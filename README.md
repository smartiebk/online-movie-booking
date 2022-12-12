# ombp
Git repository for ombp-Online Movie Booking Platform

This repository contains all projects/modules required for building micro-service based platform for online movie booking. Please browse through readme file in each folder to know more.

# Online Movie Booking Platform aka OMBP Codebase
This project consists of all micro-services, micro-modules, user-interaces, prototypes, etc. for **OMBP.**

## How to set up dev project in eclipse
- Copy https URL https://github.com/pranaypkathade/ombp.git
- Open eclipse with git plug-in
- Open **View** Git Repositories
- Right click in Git repositories' open area and **Paste** URL there by right click options.
- Enter credentials obtained by **Login to Github-->Click on top right corner-->Settings-->Developer Settings-->Personal Access Tokens-->Generate New Token**
- In eclipse use generated token as **Password**
- Click next and you will have local copy of repository as local branch in your work-station.
- Eclipse must contain Gradle plug-in.
  - In Eclipse, In project explorer, right click and select Import-->Import existing gradle project
  - Select proper location of project **ombp-business-server-ms** and click on next.
  - Import below projects with any sequence
    - **ombp-business-server-ms**
    - **ombp-ui-ms**
    - **ombp-discovery-server-ms**
    - **ombp-edge-routing-server-ms**

All projects will appear in eclipse and here, project set up is done.

## How to set up local Postgres SQL database to access applications
- Go to URL https://www.postgresql.org/download/
- And download appropriate version (Minimun 10.18 and Maximum 12.8)
- Install Postgres SQL server and remember usernames and passwords those are set up.
- Open PGAdmin and Create new database named **ombp**
- Run DDL scripts, insertion scripts.
- Once it is successful, your database set up is also done.

## How to configure applications to run
- Open application.properties in **ombp-business-server-ms** and **ombp-ui-ms**.
- Provide proper database name there with correct credentials.
- Then run only these two micro-services and you should see two spring boot applications started.
- In addition, start ombp-discovery-server-ms and ombp-edge-routing-server-ms
- Use Postman to test APIs
    
