## Synopsis

Welcome to the Luminous ID Website source code. In this directory structure, you will find all the code used to deploy luminousid.org. This website and apps asociated with it was made possible by the 'Garden Club' a 2016-17 Senior Capstone team at the University of Colorado at Boulder.  

## Code Example

This is an exmaple of a firebase database call. This code exmaple was used numerous times to retrieve and send data to our firebaase database.

```javascript
firebase.database().ref("speciesid/observations").once("value").then(function(snapshot) {
  snapshot.forEach(function(childSnapshot) {
    ...
  });
});
```

## Motivation

The effort to create this website and apps was made possible by the Computer Science Department at the University of Colorado at Boulder along with the Environmental Studies Program also at the University of Colorado at Boulder. 

## Installation

To deploy this website you'll need to install of Google Firebase-tools. Once that's installed, a simple 'firebase deploy' should allow you deploy any changes live at luminousid.org. The source code can be edited in an text editor. For any help deploying this source code, see the Firebase documentation on their website.

More documentation can be viewed at our [Google Docs](https://drive.google.com/drive/folders/0B9fTybPvTo_ILWVrbnM1ZTdyOXc?ths=true) page.

## Tests

Opening any of the pages on a local system can be used for testing.

## Contributors

Developers:
Ryan M. O'Connell
Chase Springer
D’Vreux Fontaine
Brian Larson
Kevin Rau
Jennifer Baumann

Sponsors:
Nathalie Chardon
Jane Smith

## License

All copyright and licenses can be viewed on the website at luminousid.org.
