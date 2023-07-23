Repository of course "https://www.udemy.com/course/quarkus-com-kafka/"

Functional Requirements.

BR Mining Company is a Brazilian company that operates in the iron ore extraction industry and sells raw materials in
the international market, serving clients in Europe, China, and the USA.

As an exporting company, BR Mining Company achieves better results when the US Dollar is more valued.

As part of the modernization process of the company's web applications and digital tools, there is a need to develop a
Proof of Concept (POC) for an API that allows receiving purchase proposals from clients, analyzing the exchange rate
between the Brazilian Real and the US Dollar, and creating iron ore sales opportunities based on this information.

These Sales Opportunities should be accessible directly through a REST API and should also generate CSV format reports
for Business Operators' analysis.

The basic flow is as follows:

1 - Monitoring the US Dollar exchange rate. If the Dollar is appreciating and there are sequences of appreciation of the
American currency, the updated information is sent to the database and considered in the creation of a new proposal.

2 - Entry of new purchase proposals from clients.
The proposal must contain the following data:
Client Company Name, offered value per ton of ore, quantity of tons, country of origin, proposal validity, and proposal
creation date.

2.1 - Rules regarding the proposals:

-- Only customer-type users can insert new proposals into the system.

-- An operator can view proposal details but cannot delete proposals.

-- A Manager user can view details and also delete proposals.

3 - With the information from new proposals and the current exchange rate, Sales Opportunities are created, accessible
to BR Mining Company operators in JSON format or via CSV files.