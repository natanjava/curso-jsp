# JSP-SERVLETS

About the System.
The system registers, edits, lists and deletes users in the system. At the time of registration, there are fields to be validated. 
Registration is only finalized when all fields are validated. Below the registration form, there are some users who have already been registered in the system.
It can generate reports on users and generate a salary comparison chart. The initial login to the system is done through the user super-admin 
(login: super-admin; password: superadmin; position: ADMIN; salary: $5000.00).

User Control - Acess Restriction.
For the purpose of 'business rules': Only ADMIN users can create new records. If an ADMIN is created, he cannot access to records created by another ADMIN. 
Then each ADMIN has their own list of records and consequently reports and graphs are generated with different results. Not only ADMIN, but also other types of user 
(ASSISTENT or MANAGER) can access the system, using login/password, but they cannot register new users in the system. They have no such privilege.

ZIP code validation.
In the ZIP CODE field, if the user writes a valid number for a Brazilian address, the fields 'Address, Neigborhood, City, State' will be filled in automatically. 
You can search for ZIP CODE on Google (from any Brazilian city) before filling in this field or simply write any number, and manually fill in the â€˜Address, 
Neighborhood, City, Stateâ€™ fields with any name, just for testing purposes. Do not use the '-' character in the ZIPCODE field, if you copy this number from a 
Google search. 2 examples of ZIPCODE, from two addresses in the city of Rio de Janeiro: 20021000 and 22031000.

Tools - Technical information.
Eclipse IDE, Postgresql Database, Java Server Pages and Servlets, JavaScript, JasperReports, Bootstrap, EC2 Amazon Technology.
