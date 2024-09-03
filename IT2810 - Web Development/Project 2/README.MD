# FUNN

Welcome to FUNN.

Please explore the different categories and areas, make a new user and post an advert. You should be able to locate your advert again via your profile, via searach (based on advert title), via the chosen category and via the location in Norway corresponding to the zipcode provided in making the posting.

Also, if you have problems loading different posting-tiles, please consider turning off your adblocker (or using another browser). This might be an issue for some.

### Description

This repository contains the client and server for the application _FUNN_ created by group 57 as project 2 for the course IT2810 at NTNU in autumn 2023.
_FUNN_ is a service for posting adverts regarding _Services_, _Furniture_, _Tools_, _Cars_, _Electronics_, _Property_, _Clothing_ and _Hobby_-related items!

### Table Of Contents

- [How to access](#how-to-access)
- [Functionality](#functionality)
- [Sustainability](#sustainability)
- [Accessibility](#accessibility)
- [Libraries, frameworks and technology stack](#libraries-frameworks-and-technology-stack)
- [Choices](#choices)
- [Data](#data)
- [Testing](#testing)
- [Contributors](#contributors)

### How to access

The repository is accessible at GitLab : https://gitlab.stud.idi.ntnu.no/it2810-h23/Team-57/prosjekt-2

The repository can be cloned through

- HTTP: https://gitlab.stud.idi.ntnu.no/it2810-h23/Team-57/prosjekt-2.git
- SSH: git@gitlab.stud.idi.ntnu.no:it2810-h23/Team-57/prosjekt-2.git

To install dependencies:

- Run _npm install_ in both dictionaries, _/client_ and _/server_

To start the application on your local machine.

- Navigate to _/client_ and run _npm run dev_
  This will start the client, and the client will be default connected to the server hosted at a virtual machine at NTNU. Therefore, one needs to be connected to the NTNU network.

If one wishes to run the server on your local machine, one can do so by

- Change the uri in _client/src/utilities/api.ts_ to the local server
- Navigating to _/server_ and run _npm start_

### Functionality

### Sustainability

### Accessibility

### Libraries, frameworks and technology stack

Front-end:
- [React] (https://reactjs.org/)
- [Vite] (https://vitejs.dev/)
- [SVG] (https://developer.mozilla.org/en-US/docs/Web/SVG)
- [CSS] (https://developer.mozilla.org/en-US/docs/Web/CSS)
- [Zod] (https://zod.dev/)

Testing:
- [Vitest] (https://vitest.netlify.app/)
- [Cypress] (https://www.cypress.io/)
- [Jest] (https://jestjs.io/)

Back-end:
- [React-Router] (https://reactrouter.com/)
- [GraphQL] (https://graphql.org/)
- [Apollo-Server] (https://www.apollographql.com/docs/apollo-server/)
- [Apollo-Client] (https://www.apollographql.com/docs/react/)
- [MongoDB] (https://www.mongodb.com/)
- [Node.js] (https://nodejs.org/en/)
- [Mongoose] (https://mongoosejs.com/)

Development Tools:
- [Prettier] (https://prettier.io/)
- [ESLint] (https://eslint.org/)

Other/Both:
- [TypeScript] (https://www.typescriptlang.org/)
- [Geonorge] (https://www.geonorge.no/)



### Choices

### Data

The data that makes up the basis of the application _FUNN_ is user-generated and stored in a MongoDB database hosted on the group's virtual machine at NTNU.
This includes data on _listings_ and data on _users_-

Two APIs provided by _geonorge_ are used to translate a user input of _zipode_ into a municipality and _region_ (_Område_) which is used to filter listing based on area.

### Testing

For testing the client, you need to navigate to _/client_.

##### Component

Components have been tested through the testing of the _Results_ page and the _NewProfile_ page.
Through testing the _Results_ page expected output of sorting the components has been verified, as well as the rendering of the page would render the loading state or error state.
Through testing the _NewProfile_ page the functionality and validity of the form, and the package _ZOD_ used to validate the form, have been tested for various encounters.

To run the tests testing components and the snapshot test, run _npm run test_.

##### End-to-end

End-to-end testing has been implemented using [Cypress](https://docs.cypress.io/guides/overview/why-cypress).
End-to-end testing has enabled us to test for user interaction with the database, as well as functionality that depends on the database, such as search-to-database.
It tests search from the landing page which yields a result, search from the landing page which does not yield a result, choosing a category from the landing page, search from the results page, and viewing all listings and changing pages with the paginator component.

To run the end-to-end test, you need to start an instance of the application in your browser (run _npm run dev_) and then run _npm run cypress:open_ to start cypress. To run the end-to-end tests, you need to make sure the client is connected to a server, see [How to access](#how-to-access).
Then, follow the instructions in the browser opened by cypress.

##### Snapshot

A snapshot test is included to test whether the application renders as it should according to the snapshot.
No other uses were found for the snapshot test during the development phase of this application.

### Contributors

Timon Alexander Selnes - @timonas

Mikkel Emil Lange Friis - @mefriis

Kristina Lawas Wessel Kristoffersen - @klkristo