package servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;  
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import beandto.BeanDtoGraficoSalarioUser;
import dao.DAOUsuarioRepository;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.ModelLogin;
import util.ReportUtil;

@MultipartConfig
@javax.servlet.annotation.WebServlet( urlPatterns =  {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
	
	private static final long serialVersionUID = 1L;
	
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

    public ServletUsuarioController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {	
		
		 String acao  = request.getParameter("acao");
		 
		 if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) { 
			 
			 String idUser = request.getParameter("id");
			 
			 daoUsuarioRepository.deletarUser(idUser);
			 
			 List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getLoggedUser(request));
		     request.setAttribute("modelLogins", modelLogins);
		     
			 request.setAttribute("msg", "Successfully deleted!");
			 request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getLoggedUser(request)));
			 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		 }
		 else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				 
				 String idUser = request.getParameter("id");
				 
				 daoUsuarioRepository.deletarUser(idUser);
				 
				 response.getWriter().write("Successfully deleted!");
			 
		 }
		 
		 else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
			 
			 String nomeBusca = request.getParameter("nomeBusca");
			 
			 List<ModelLogin> dadosJsonUser =  daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getLoggedUser(request));
			 
			 ObjectMapper mapper = new ObjectMapper();
			 
			 String json = mapper.writeValueAsString(dadosJsonUser);
			 
			 response.addHeader("totalPagina", ""+ daoUsuarioRepository.consultaUsuarioListTotalPaginaPaginacao(nomeBusca, super.getLoggedUser(request)));
			 response.getWriter().write(json);
			 
		 }
		 
		 	else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {
			 
			 String nomeBusca = request.getParameter("nomeBusca");
			 String pagina = request.getParameter("pagina");
			 
			 List<ModelLogin> dadosJsonUser =  daoUsuarioRepository.consultaUsuarioListOffSet(nomeBusca, super.getLoggedUser(request), Integer.parseInt(pagina));
			 
			 ObjectMapper mapper = new ObjectMapper();
			 
			 String json = mapper.writeValueAsString(dadosJsonUser);
			 
			 response.addHeader("totalPagina", ""+ daoUsuarioRepository.consultaUsuarioListTotalPaginaPaginacao(nomeBusca, super.getLoggedUser(request)));
			 response.getWriter().write(json);
			 
		 	}
		 
		 else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
			
			    String id = request.getParameter("id");
			 
			     ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id, super.getLoggedUser(request));
			 
			     List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getLoggedUser(request));
			     request.setAttribute("modelLogins", modelLogins);
			     
			    request.setAttribute("msg", "Editing user.");
				request.setAttribute("modolLogin", modelLogin);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getLoggedUser(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		 }
		 
		 else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
			 
			 List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getLoggedUser(request));
			 
			 request.setAttribute("msg", "Users already saved in the system.");
		     request.setAttribute("modelLogins", modelLogins);
		     request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getLoggedUser(request)));
			 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			 
		 }
		 else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
			 
			 String idUser = request.getParameter("id");
			 
			 ModelLogin modelLogin =  daoUsuarioRepository.consultaUsuarioID(idUser, super.getLoggedUser(request));
			 if (modelLogin.getFotouser() != null && !modelLogin.getFotouser().isEmpty()) {
				 
				 response.setHeader("Content-Disposition", "attachment;filename=photo-employee." + modelLogin.getExtensaofotouser());
				 response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotouser().split("\\,")[1]));
				 
			 }
			 
		 }
		 else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
			 Integer offset = Integer.parseInt(request.getParameter("pagina"));
			 
			 List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioListPaginada(this.getLoggedUser(request), offset);
			 
			 request.setAttribute("modelLogins", modelLogins);
		     request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getLoggedUser(request)));
			 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		 }
		 else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")) {
			 
			 String dataInicial = request.getParameter("dataInicial");
			 String dataFinal = request.getParameter("dataFinal");
			 
			
			 if (dataInicial == null || dataInicial.isEmpty() 
					 && dataFinal == null || dataFinal.isEmpty()) {
				 
				 request.setAttribute("listaUser", daoUsuarioRepository.consultaUsuarioListRel(super.getLoggedUser(request)));
				 
			 }else {
				
				 request.setAttribute("listaUser", daoUsuarioRepository
						 .consultaUsuarioListRel(super.getLoggedUser(request), dataInicial, dataFinal));
				 
			 }
			 
			 
			 request.setAttribute("dataInicial", dataInicial);
			 request.setAttribute("dataFinal", dataFinal);
			 request.getRequestDispatcher("principal/reluser.jsp").forward(request, response);
			 
		 }
		 
		 else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF")) {
			 
			 String dataInicial = request.getParameter("dataInicial");
			 String dataFinal = request.getParameter("dataFinal");
			 
			 List<ModelLogin> modelLogins = null;
			 
			 if (dataInicial == null || dataInicial.isEmpty() 
					 && dataFinal == null || dataFinal.isEmpty()) {
				 
				 modelLogins = daoUsuarioRepository.consultaUsuarioListRel(super.getLoggedUser(request));
				 
			 }else {
				 
				 modelLogins = daoUsuarioRepository
						 .consultaUsuarioListRel(super.getLoggedUser(request), dataInicial, dataFinal);
			 }
			 
			 
			 HashMap<String, Object> params = new HashMap<String, Object>();
			 params.put("PARAM_SUB_REPORT", request.getServletContext().getRealPath("relatorio") + File.separator);
			 
			 byte[] relatorio = new ReportUtil().geraReltorioPDF(modelLogins, "resl-user-jsp", params ,request.getServletContext());
			 
			 
			 response.setHeader("Content-Disposition", "attachment;filename=PDF-report.pdf");
			 response.getOutputStream().write(relatorio);
			 
		 }
		 
		 else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("graficoSalario")) {
			 
			 String dataInicial = request.getParameter("dataInicial");
			 String dataFinal = request.getParameter("dataFinal");
			 
			 
			 if (dataInicial == null || dataInicial.isEmpty() 
					 && dataFinal == null || dataFinal.isEmpty()) {
				 
				 
				 BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser =   daoUsuarioRepository.
						 montarGraficoMediaSalario(super.getLoggedUser(request));
				 
				   ObjectMapper mapper = new ObjectMapper();
				 
				   String json = mapper.writeValueAsString(beanDtoGraficoSalarioUser);
				 
				   response.getWriter().write(json);
				 
				 
			 }else {
				 
				 BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser =   daoUsuarioRepository.
						 montarGraficoMediaSalario(super.getLoggedUser(request), dataInicial, dataFinal);
				 
				   ObjectMapper mapper = new ObjectMapper();
				 
				   String json = mapper.writeValueAsString(beanDtoGraficoSalarioUser);
				 
				   response.getWriter().write(json);
				 
		
			 }
			 
			 
			 	
			 
		 }
		
		 else {
			 List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getLoggedUser(request));
		     request.setAttribute("modelLogins", modelLogins);
		     request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getLoggedUser(request)));
			 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		 }
		 
		
		 
		 
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
		String msg = "Action performed successfully!";	
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String perfil = request.getParameter("perfil");
		String sexo = request.getParameter("sexo");
		String cep = request.getParameter("cep");
		String logradouro = request.getParameter("logradouro");
		String bairro = request.getParameter("bairro");
		String localidade = request.getParameter("localidade");
		String uf = request.getParameter("uf");
		String numero = request.getParameter("numero");
		String dataNascimento = request.getParameter("dataNascimento");
		String rendaMensal = request.getParameter("rendamensal");
		
		rendaMensal = rendaMensal.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		
		ModelLogin modelLogin = new ModelLogin();
		
		modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
		modelLogin.setNome(nome);
		modelLogin.setEmail(email);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		modelLogin.setPerfil(perfil);
		modelLogin.setSexo(sexo);
		modelLogin.setCep(cep);
		modelLogin.setLogradouro(logradouro);
		modelLogin.setBairro(bairro);
		modelLogin.setLocalidade(localidade);
		modelLogin.setUf(uf);
		modelLogin.setNumero(numero);
		modelLogin.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento))));
		modelLogin.setRendamensal(Double.valueOf(rendaMensal));
		
		if (request.getPart("fileFoto") != null) {
			
			Part part = request.getPart("fileFoto"); /*Pega foto da tela*/
			
			if (part.getSize() > 0) {
				byte[] foto = IOUtils.toByteArray(part.getInputStream()); /*Converte imagem para byte*/
				String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," +  new Base64().encodeBase64String(foto);
				
				modelLogin.setFotouser(imagemBase64);
				modelLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]);
			}
			
		}
		
		if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
			msg = "There is already a user with the same login, please inform another login.";
		}else {
			if (modelLogin.isNovo()) {
				msg = "User registered successfully.";
			}else {
				msg= "User updated successfully.";
			}
			
		    modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getLoggedUser(request));
		}
		
		
		 List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getLoggedUser(request));
	     request.setAttribute("modelLogins", modelLogins);
		 request.setAttribute("msg", msg);
		 request.setAttribute("modolLogin", modelLogin);
		 request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getLoggedUser(request)));
		
		 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}
