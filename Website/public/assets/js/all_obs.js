
document.body.style.backgroundColor = "#384452";

var recent_obs_data = firebase.database().ref("speciesid/observations").orderByKey();

recent_obs_data.once("value").then(function(snapshot) {
    snapshot.forEach(function(childSnapshot) {

        var species_name = childSnapshot.val().species_name;
        var datetime = childSnapshot.val().datetime;
        var username = childSnapshot.val().username;

        var h5_1 = document.createElement('h4');
        h5_1.style = "float:left;color:#dddddd;width:33%;font-size:16px;text-align:left";
        var text_1 = document.createTextNode(species_name);
        h5_1.appendChild(text_1);
        document.body.appendChild(h5_1);

        var h5_2 = document.createElement('h4');
        h5_2.style = "float:left;color:#dddddd;width:33%;font-size:16px;text-align:center";
        var text_2 = document.createTextNode(username);
        h5_2.appendChild(text_2);
        document.body.appendChild(h5_2);

        var h5_3 = document.createElement('h4');
        h5_3.style = "float:left;color:#dddddd;width:33%;font-size:16px;text-align:right";
        var text_3 = document.createTextNode(datetime);
        h5_3.appendChild(text_3);
        document.body.appendChild(h5_3);

    });
});