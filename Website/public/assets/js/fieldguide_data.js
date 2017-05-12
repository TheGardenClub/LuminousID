// On load, set up the entire list of items. 
(function() {


    document.body.style.backgroundColor = "#384452";


    var dis_plant_code = parent.document.getElementById("plant_code")
    var display_species = parent.document.getElementById("display_species");
    var display_common_name = parent.document.getElementById("display_common_name");
    var display_family_name = parent.document.getElementById("display_family_name");
    var display_synonyms = parent.document.getElementById("display_synonyms");
    var display_growth_form = parent.document.getElementById("display_growth_form");
    var display_flower_color = parent.document.getElementById("display_flower_color");
    var display_flower_shape = parent.document.getElementById("display_flower_shape");
    var display_habitat = parent.document.getElementById("display_habitat");
    var display_leaf_arrangement = parent.document.getElementById("display_leaf_arrangement");
    var display_leaf_shape_filter = parent.document.getElementById("display_leaf_shape_filter");
    var display_petal_number = parent.document.getElementById("display_petal_number");
    var display_notes = parent.document.getElementById("display_notes");
    var display_photo_credit = parent.document.getElementById("display_photo_credit");
    var display_inflorescence = parent.document.getElementById("display_inflorescence");
    var display_leaf_blade = parent.document.getElementById("display_leaf_blade");
    var display_spike_color = parent.document.getElementById("display_spike_color");
    var display_stem_cross_section = parent.document.getElementById("display_stem_cross_section");
    var display_awns = parent.document.getElementById("display_awns");
    var display_florets_per_spikelet = parent.document.getElementById("display_florets_per_spikelet");
    var display_cone = parent.document.getElementById("display_cone");
    var display_leaf_margin = parent.document.getElementById("display_leaf_margin");
    var display_leaf_shape = parent.document.getElementById("display_leaf_shape");
    var display_leaf_type = parent.document.getElementById("display_leaf_type");
    var display_needle_apex = parent.document.getElementById("display_needle_apex");
    var display_needle_arrangement = parent.document.getElementById("display_needle_arrangement");
    var display_needle_per_fascile = parent.document.getElementById("display_needle_per_fascile");


    var forbs_query = firebase.database().ref("speciesid/field_guide/forbs").orderByKey();
    forbs_query.once("value")
        .then(function(snapshot) {
            snapshot.forEach(function(childSnapshot) {

                /* Forbs */
                var flowerColorName = childSnapshot.val().flower_color;
                var flowerShapeName = childSnapshot.val().flower_shape;
                var leafArrangementName = childSnapshot.val().leaf_arrangement;
                var leafShapeFilterName = childSnapshot.val().leaf_shape_filter;
                var petalNumberName = childSnapshot.val().petal_number;
                var speciesName = childSnapshot.val().species_name;
                var commonName = childSnapshot.val().common_name;
                var familyName = childSnapshot.val().family_name;
                var notesName = childSnapshot.val().notes;
                var photoCreditName = childSnapshot.val().photo_credit;
                var synonymsName = childSnapshot.val().synonyms;
                var growthFormName = childSnapshot.val().growth_form;
                var habitatName = childSnapshot.val().habitat;
                var plantcode = childSnapshot.val().plant_code;
                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {
                    dis_plant_code.textContent = plantcode;
                    display_species.textContent = speciesName;
                    display_common_name.textContent = commonName;
                    display_family_name.textContent = familyName;
                    display_synonyms.textContent = synonymsName;
                    display_growth_form.textContent = growthFormName;
                    display_flower_color.textContent = flowerColorName;
                    display_flower_shape.textContent = flowerShapeName;
                    display_habitat.textContent = habitatName;
                    display_leaf_arrangement.textContent = leafArrangementName;
                    display_leaf_shape_filter.textContent = leafShapeFilterName;
                    display_petal_number.textContent = petalNumberName;
                    display_notes.textContent = notesName;
                    display_photo_credit.textContent = photoCreditName;
                };

                var h5 = document.createElement('h5');
                var text2 = document.createTextNode(commonName);
                h5.appendChild(text2);
                document.body.appendChild(h5);

            });

        });

    var graminoids_query_one = firebase.database().ref("speciesid/field_guide/graminoids/cyperaceae").orderByKey();
    graminoids_query_one.once("value")
        .then(function(snapshot) {
            snapshot.forEach(function(childSnapshot) {

                /* Graminoids - cyperaceae */
                var speciesName = childSnapshot.val().species_name;
                var commonName = childSnapshot.val().common_name;
                var familyName = childSnapshot.val().family_name;
                var notesName = childSnapshot.val().notes;
                var photoCreditName = childSnapshot.val().photo_credit;
                var synonymsName = childSnapshot.val().synonyms;
                var growthFormName = childSnapshot.val().growth_form;
                var inflorescenceName = childSnapshot.val().inflorescence;
                var leafBladeName = childSnapshot.val().leaf_blade;
                var spikeColorName = childSnapshot.val().spike_color;
                var stemCrossSectionName = childSnapshot.val().stem_cross_section;
                var habitatName = childSnapshot.val().habitat;
                var plantcode = childSnapshot.val().plant_code;
                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {
                    dis_plant_code.textContent = plantcode;
                    display_species.textContent = speciesName;
                    display_common_name.textContent = commonName;
                    display_family_name.textContent = familyName;
                    display_synonyms.textContent = synonymsName;
                    display_growth_form.textContent = growthFormName;
                    display_inflorescence.textContent = inflorescenceName;
                    display_leaf_blade.textContent = leafBladeName;
                    display_habitat.textContent = habitatName;
                    display_spike_color.textContent = spikeColorName;
                    display_stem_cross_section.textContent = stemCrossSectionName;
                    display_notes.textContent = notesName;
                    display_photo_credit.textContent = photoCreditName;

                };

                var h5 = document.createElement('h5');
                var text2 = document.createTextNode(commonName);
                h5.appendChild(text2);
                document.body.appendChild(h5);

            });

        });

    var graminoids_query_two = firebase.database().ref("speciesid/field_guide/graminoids/juncaceae").orderByKey();
    graminoids_query_two.once("value")
        .then(function(snapshot) {
            snapshot.forEach(function(childSnapshot) {

                /* Graminoids - juncaceae */
                var speciesName = childSnapshot.val().species_name;
                var commonName = childSnapshot.val().common_name;
                var familyName = childSnapshot.val().family_name;
                var notesName = childSnapshot.val().notes;
                var photoCreditName = childSnapshot.val().photo_credit;
                var synonymsName = childSnapshot.val().synonyms;
                var growthFormName = childSnapshot.val().growth_form;
                var leafBladeName = childSnapshot.val().leaf_blade;
                var stemCrossSectionName = childSnapshot.val().stem_cross_section;
                var habitatName = childSnapshot.val().habitat;
                var plantcode = childSnapshot.val().plant_code;
                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {
                    dis_plant_code.textContent = plantcode;
                    display_species.textContent = speciesName;
                    display_common_name.textContent = commonName;
                    display_family_name.textContent = familyName;
                    display_synonyms.textContent = synonymsName;
                    display_growth_form.textContent = growthFormName;
                    display_leaf_blade.textContent = leafBladeName;
                    display_habitat.textContent = habitatName;
                    display_stem_cross_section.textContent = stemCrossSectionName;
                    display_notes.textContent = notesName;
                    display_photo_credit.textContent = photoCreditName;

                };

                var h5 = document.createElement('h5');
                var text2 = document.createTextNode(commonName);
                h5.appendChild(text2);
                document.body.appendChild(h5);

            });

        });

    var graminoids_query_three = firebase.database().ref("speciesid/field_guide/graminoids/poaceae").orderByKey();
    graminoids_query_three.once("value")
        .then(function(snapshot) {
            snapshot.forEach(function(childSnapshot) {

                /* Graminoids - Poaceae */
                var speciesName = childSnapshot.val().species_name;
                var commonName = childSnapshot.val().common_name;
                var familyName = childSnapshot.val().family_name;
                var notesName = childSnapshot.val().notes;
                var photoCreditName = childSnapshot.val().photo_credit;
                var synonymsName = childSnapshot.val().synonyms;
                var growthFormName = childSnapshot.val().growth_form;
                var awnsName = childSnapshot.val().awns;
                var floretsPerSpikeletName = childSnapshot.val().florets_per_spikelet;
                var inflorescenceName = childSnapshot.val().inflorescence;
                var leafBladeName = childSnapshot.val().leaf_blade;
                var stemCrossSectionName = childSnapshot.val().stem_cross_section;
                var habitatName = childSnapshot.val().habitat;
                var plantcode = childSnapshot.val().plant_code;
                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {
                    dis_plant_code.textContent = plantcode;
                    display_species.textContent = speciesName;
                    display_common_name.textContent = commonName;
                    display_family_name.textContent = familyName;
                    display_synonyms.textContent = synonymsName;
                    display_growth_form.textContent = growthFormName;
                    display_awns.textContent = awnsName;
                    display_florets_per_spikelet.textContent = floretsPerSpikeletName;
                    display_inflorescence.textContent = inflorescenceName;
                    display_leaf_blade.textContent = leafBladeName;
                    display_habitat.textContent = habitatName;
                    display_stem_cross_section.textContent = stemCrossSectionName;
                    display_notes.textContent = notesName;
                    display_photo_credit.textContent = photoCreditName;

                };

                var h5 = document.createElement('h5');
                var text2 = document.createTextNode(commonName);
                h5.appendChild(text2);
                document.body.appendChild(h5);

            });

        });

    var woody_query_one = firebase.database().ref("speciesid/field_guide/woody/deciduous").orderByKey();
    woody_query_one.once("value")
        .then(function(snapshot) {
            snapshot.forEach(function(childSnapshot) {

                /* Woodys - deciduous */
                var speciesName = childSnapshot.val().species_name;
                var commonName = childSnapshot.val().common_name;
                var familyName = childSnapshot.val().family_name;
                var notesName = childSnapshot.val().notes;
                var photoCreditName = childSnapshot.val().photo_credit;
                var synonymsName = childSnapshot.val().synonyms;
                var growthFormName = childSnapshot.val().growth_form;
                var coneName = childSnapshot.val().cone;
                var leafArrangementName = childSnapshot.val().leaf_arrangement;
                var leafMarginName = childSnapshot.val().leaf_margin;
                var leafShapeName = childSnapshot.val().leaf_shape;
                var leafTypeName = childSnapshot.val().leaf_type;
                var plantcode = childSnapshot.val().plant_code;
                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {
                    dis_plant_code.textContent = plantcode;
                    display_species.textContent = speciesName;
                    display_common_name.textContent = commonName;
                    display_family_name.textContent = familyName;
                    display_synonyms.textContent = synonymsName;
                    display_growth_form.textContent = growthFormName;
                    display_cone.textContent = coneName;
                    display_leaf_arrangement.textContent = leafArrangementName;
                    display_leaf_margin.textContent = leafMarginName;
                    display_leaf_shape.textContent = leafShapeName;
                    display_leaf_type.textContent = leafTypeName;
                    display_notes.textContent = notesName;
                    display_photo_credit.textContent = photoCreditName;

                };

                var h5 = document.createElement('h5');
                var text2 = document.createTextNode(commonName);
                h5.appendChild(text2);
                document.body.appendChild(h5);

            });

        });

    var woody_query_two = firebase.database().ref("speciesid/field_guide/woody/needle").orderByKey();
    woody_query_two.once("value")
        .then(function(snapshot) {
            snapshot.forEach(function(childSnapshot) {

                /* Woodys - Needle */
                var speciesName = childSnapshot.val().species_name;
                var commonName = childSnapshot.val().common_name;
                var familyName = childSnapshot.val().family_name;
                var notesName = childSnapshot.val().notes;
                var photoCreditName = childSnapshot.val().photo_credit;
                var synonymsName = childSnapshot.val().synonyms;
                var growthFormName = childSnapshot.val().growth_form;
                var coneName = childSnapshot.val().cone;
                var needleApexName = childSnapshot.val().needle_apex;
                var needleArrangementName = childSnapshot.val().needle_arrangement;
                var needlePerFascileName = childSnapshot.val().needle_per_fascile;
                var leafTypeName = childSnapshot.val().leaf_type;
                var plantcode = childSnapshot.val().plant_code;
                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {
                    dis_plant_code.textContent = plantcode;
                    display_species.textContent = speciesName;
                    display_common_name.textContent = commonName;
                    display_family_name.textContent = familyName;
                    display_synonyms.textContent = synonymsName;
                    display_growth_form.textContent = growthFormName;
                    display_cone.textContent = coneName;
                    display_needle_apex.textContent = needleApexName;
                    display_needle_arrangement.textContent = needleArrangementName;
                    display_needle_per_fascile.textContent = needlePerFascileName;
                    display_leaf_type.textContent = leafTypeName;
                    display_notes.textContent = notesName;
                    display_photo_credit.textContent = photoCreditName;

                };

                var h5 = document.createElement('h5');
                var text2 = document.createTextNode(commonName);
                h5.appendChild(text2);
                document.body.appendChild(h5);

            });

        });

}());
// Running every so often (every 500 ms), update based on values. 
setInterval(function() {
    if (parent.document.getElementById("change").textContent == "true") {

        document.body.innerHTML = "";
        document.body.style.backgroundColor = "#384452";
        parent.document.getElementById("change").textContent = 'false'

        var dis_plant_code = parent.document.getElementById("plant_code")
        var display_species = parent.document.getElementById("display_species");
        var display_common_name = parent.document.getElementById("display_common_name");
        var display_family_name = parent.document.getElementById("display_family_name");
        var display_synonyms = parent.document.getElementById("display_synonyms");
        var display_growth_form = parent.document.getElementById("display_growth_form");
        var display_flower_color = parent.document.getElementById("display_flower_color");
        var display_flower_shape = parent.document.getElementById("display_flower_shape");
        var display_habitat = parent.document.getElementById("display_habitat");
        var display_leaf_arrangement = parent.document.getElementById("display_leaf_arrangement");
        var display_leaf_shape_filter = parent.document.getElementById("display_leaf_shape_filter");
        var display_petal_number = parent.document.getElementById("display_petal_number");
        var display_notes = parent.document.getElementById("display_notes");
        var display_photo_credit = parent.document.getElementById("display_photo_credit");
        var display_inflorescence = parent.document.getElementById("display_inflorescence");
        var display_leaf_blade = parent.document.getElementById("display_leaf_blade");
        var display_spike_color = parent.document.getElementById("display_spike_color");
        var display_stem_cross_section = parent.document.getElementById("display_stem_cross_section");
        var display_awns = parent.document.getElementById("display_awns");
        var display_florets_per_spikelet = parent.document.getElementById("display_florets_per_spikelet");
        var display_cone = parent.document.getElementById("display_cone");
        var display_leaf_margin = parent.document.getElementById("display_leaf_margin");
        var display_leaf_shape = parent.document.getElementById("display_leaf_shape");
        var display_leaf_type = parent.document.getElementById("display_leaf_type");
        var display_needle_apex = parent.document.getElementById("display_needle_apex");
        var display_needle_arrangement = parent.document.getElementById("display_needle_arrangement");
        var display_needle_per_fascile = parent.document.getElementById("display_needle_per_fascile");
        var par_category = parent.document.getElementById("category");
        var par_cat2 = parent.document.getElementById("cat2");
        var par_subcat = parent.document.getElementById("subcat").innerHTML;
        var par_fil = parent.document.getElementById("fil").innerHTML;
        var par_subcat_array = par_subcat.split(',');
        var par_fil_array = par_fil.split(',');
        var the_length = par_subcat_array.length;
        if (par_category.textContent == "Forbs" || par_category.textContent == "N/A") {
            var forbs_query = firebase.database().ref("speciesid/field_guide/forbs").orderByKey();
            forbs_query.once("value")
                .then(function(snapshot) {
                    snapshot.forEach(function(childSnapshot) {

                        /* Forbs */
                        // ryanmidigationarray is there to hold the values so that we can seach over everything and apply the filters.
                        var flowerColorName = childSnapshot.val().flower_color;
                        var flowerShapeName = childSnapshot.val().flower_shape;
                        var leafArrangementName = childSnapshot.val().leaf_arrangement;
                        var leafShapeFilterName = childSnapshot.val().leaf_shape_filter;
                        var petalNumberName = childSnapshot.val().petal_number;
                        var speciesName = childSnapshot.val().species_name;
                        var commonName = childSnapshot.val().common_name;
                        var familyName = childSnapshot.val().family_name;
                        var notesName = childSnapshot.val().notes;
                        var photoCreditName = childSnapshot.val().photo_credit;
                        var synonymsName = childSnapshot.val().synonyms;
                        var growthFormName = childSnapshot.val().growth_form;
                        var habitatName = childSnapshot.val().habitat;
                        var plantcode = childSnapshot.val().plant_code;
                        var ryanmidigatearray = [flowerShapeName, leafShapeFilterName, petalNumberName, familyName, growthFormName, '63']
                        var habitatNameArray = habitatName.split(',')
                        var colorNameAray = flowerColorName.split(',')
                        var petalNumberNameArray = petalNumberName.split(',')
                        var leafArrangementNameArray = leafArrangementName.split(',')
                        var testarray = [];
                        var habitatNameArraylen = habitatNameArray.length;
                        var cnan = colorNameAray.length;
                        var pnnan = petalNumberNameArray.length;
                        var lana = leafArrangementNameArray.length;
                        // Very ugly code to put each multi-entry into the array properly. 
                        for (var t = 0; t < habitatNameArraylen; t++) {
                            testarray[t] = habitatNameArray[t].trim()
                        }

                        ryanmidigatearray = ryanmidigatearray.concat(testarray)
                        for (var t = 0; t < pnnan; t++) {
                            testarray[t] = petalNumberNameArray[t].trim()
                        }
                        ryanmidigatearray = ryanmidigatearray.concat(testarray)
                        for (var t = 0; t < cnan; t++) {
                            testarray[t] = colorNameAray[t].trim()
                        }
                        ryanmidigatearray = ryanmidigatearray.concat(testarray)

                        for (var t = 0; t < lana; t++) {
                            testarray[t] = leafArrangementNameArray[t].trim()
                        }
                        ryanmidigatearray = ryanmidigatearray.concat(testarray)
                        var valid = true;
                        // Check if any filter currently doesn't match, change valid to false if it doesn't match
                        for (var i = 0; i < the_length; i++) {
                            var filtname = par_subcat_array[i]
                            var filtprop = par_fil_array[i]
                            if (ryanmidigatearray.indexOf(filtprop) != -1) {
                                continue
                            } else {
                                valid = false;

                            }

                        }
                        // show entries that match filters.
                        if (valid) {
                            var h6 = document.createElement('h6');
                            var text = document.createTextNode(speciesName);
                            h6.appendChild(text);
                            document.body.appendChild(h6);

                            h6.onclick = function() {
                                dis_plant_code.textContent = plantcode;
                                display_species.textContent = speciesName;
                                display_common_name.textContent = commonName;
                                display_family_name.textContent = familyName;
                                display_synonyms.textContent = synonymsName;
                                display_growth_form.textContent = growthFormName;
                                display_flower_color.textContent = flowerColorName;
                                display_flower_shape.textContent = flowerShapeName;
                                display_habitat.textContent = habitatName;
                                display_leaf_arrangement.textContent = leafArrangementName;
                                display_leaf_shape_filter.textContent = leafShapeFilterName;
                                display_petal_number.textContent = petalNumberName;
                                display_notes.textContent = notesName;
                                display_photo_credit.textContent = photoCreditName;
                            };

                            var h5 = document.createElement('h5');
                            var text2 = document.createTextNode(commonName);
                            h5.appendChild(text2);
                            document.body.appendChild(h5);

                        }

                    });

                });
        };
        if (par_category.textContent === "Gram" || par_category.textContent == "N/A") {
            if (par_cat2.textContent === "cyperaceae" || par_cat2.textContent === "N/A") {
                var graminoids_query_one = firebase.database().ref("speciesid/field_guide/graminoids/cyperaceae").orderByKey();
                graminoids_query_one.once("value")
                    .then(function(snapshot) {
                        snapshot.forEach(function(childSnapshot) {

                            /* Graminoids - cyperaceae */
                            var speciesName = childSnapshot.val().species_name;
                            var commonName = childSnapshot.val().common_name;
                            var familyName = childSnapshot.val().family_name;
                            var plantcode = childSnapshot.val().plant_code;
                            var notesName = childSnapshot.val().notes;
                            var photoCreditName = childSnapshot.val().photo_credit;
                            var synonymsName = childSnapshot.val().synonyms;
                            var growthFormName = childSnapshot.val().growth_form;
                            var inflorescenceName = childSnapshot.val().inflorescence;
                            var leafBladeName = childSnapshot.val().leaf_blade;
                            var spikeColorName = childSnapshot.val().spike_color;
                            var stemCrossSectionName = childSnapshot.val().stem_cross_section;
                            var habitatName = childSnapshot.val().habitat;
                            var ryanmidigatearray = [familyName, growthFormName, inflorescenceName, stemCrossSectionName, '63']
                            var valid = true;
                            var habitatNameArray = habitatName.split(',')
                            var spikeColorNameArray = spikeColorName.split(',')
                            var leafBladeNameArray = leafBladeName.split(',')
                            var testarray = [];
                            var habitatNameArraylen = habitatNameArray.length;
                            var scnan = spikeColorNameArray.length
                            var lbna = leafBladeNameArray.length
                                // same as fobs
                            for (var t = 0; t < habitatNameArraylen; t++) {
                                testarray[t] = habitatNameArray[t].trim()
                            }

                            ryanmidigatearray = ryanmidigatearray.concat(testarray)
                            for (var t = 0; t < scnan; t++) {
                                testarray[t] = spikeColorNameArray[t].trim()
                            }

                            ryanmidigatearray = ryanmidigatearray.concat(testarray)

                            for (var t = 0; t < lbna; t++) {
                                testarray[t] = leafBladeNameArray[t].trim()
                            }

                            ryanmidigatearray = ryanmidigatearray.concat(testarray)

                            for (var i = 0; i < the_length; i++) {
                                var filtname = par_subcat_array[i]
                                var filtprop = par_fil_array[i]
                                if (ryanmidigatearray.indexOf(filtprop) !== -1) {
                                    continue
                                } else {
                                    valid = false;
                                }

                            }
                            if (valid) {
                                var h6 = document.createElement('h6');
                                var text = document.createTextNode(speciesName);
                                h6.appendChild(text);
                                document.body.appendChild(h6);

                                h6.onclick = function() {
                                    dis_plant_code.textContent = plantcode;
                                    display_species.textContent = speciesName;
                                    display_common_name.textContent = commonName;
                                    display_family_name.textContent = familyName;
                                    display_synonyms.textContent = synonymsName;
                                    display_growth_form.textContent = growthFormName;
                                    display_inflorescence.textContent = inflorescenceName;
                                    display_leaf_blade.textContent = leafBladeName;
                                    display_habitat.textContent = habitatName;
                                    display_spike_color.textContent = spikeColorName;
                                    display_stem_cross_section.textContent = stemCrossSectionName;
                                    display_notes.textContent = notesName;
                                    display_photo_credit.textContent = photoCreditName;

                                };

                                var h5 = document.createElement('h5');
                                var text2 = document.createTextNode(commonName);
                                h5.appendChild(text2);
                                document.body.appendChild(h5);

                            }
                        });

                    });
            }
            if (par_cat2.textContent === "juncaceae" || par_cat2.textContent == 'N/A') {
                var graminoids_query_two = firebase.database().ref("speciesid/field_guide/graminoids/juncaceae").orderByKey();
                graminoids_query_two.once("value")
                    .then(function(snapshot) {
                        snapshot.forEach(function(childSnapshot) {

                            /* Graminoids - juncaceae */
                            var speciesName = childSnapshot.val().species_name;
                            var commonName = childSnapshot.val().common_name;
                            var plantcode = childSnapshot.val().plant_code;
                            var familyName = childSnapshot.val().family_name;
                            var notesName = childSnapshot.val().notes;
                            var photoCreditName = childSnapshot.val().photo_credit;
                            var synonymsName = childSnapshot.val().synonyms;
                            var growthFormName = childSnapshot.val().growth_form;
                            var leafBladeName = childSnapshot.val().leaf_blade;
                            var stemCrossSectionName = childSnapshot.val().stem_cross_section;
                            var habitatName = childSnapshot.val().habitat;
                            var ryanmidigatearray = [familyName, growthFormName, stemCrossSectionName, '63']
                            var valid = true;
                            var habitatNameArray = habitatName.split(',')
                            var leafBladeNameArray = leafBladeName.split(',')
                            var testarray = [];
                            var habitatNameArraylen = habitatNameArray.length;
                            var lbna = leafBladeNameArray.length;
                            // same as forbs
                            for (var t = 0; t < habitatNameArraylen; t++) {
                                testarray[t] = habitatNameArray[t].trim()
                            }

                            ryanmidigatearray = ryanmidigatearray.concat(testarray)
                            for (var t = 0; t < lbna; t++) {
                                testarray[t] = leafBladeNameArray[t].trim()
                            }

                            ryanmidigatearray = ryanmidigatearray.concat(testarray)

                            for (var i = 0; i < the_length; i++) {
                                var filtname = par_subcat_array[i]
                                var filtprop = par_fil_array[i]
                                if (ryanmidigatearray.indexOf(filtprop) != -1) {
                                    continue
                                } else {
                                    valid = false;
                                }

                            }
                            if (valid) {
                                var h6 = document.createElement('h6');
                                var text = document.createTextNode(speciesName);
                                h6.appendChild(text);
                                document.body.appendChild(h6);

                                h6.onclick = function() {
                                    dis_plant_code.textContent = plantcode;
                                    display_species.textContent = speciesName;
                                    display_common_name.textContent = commonName;
                                    display_family_name.textContent = familyName;
                                    display_synonyms.textContent = synonymsName;
                                    display_growth_form.textContent = growthFormName;
                                    display_leaf_blade.textContent = leafBladeName;
                                    display_habitat.textContent = habitatName;
                                    display_stem_cross_section.textContent = stemCrossSectionName;
                                    display_notes.textContent = notesName;
                                    display_photo_credit.textContent = photoCreditName;

                                };

                                var h5 = document.createElement('h5');
                                var text2 = document.createTextNode(commonName);
                                h5.appendChild(text2);
                                document.body.appendChild(h5);

                            }
                        });

                    });
            }
            if (par_cat2.textContent === "poaceae" || par_cat2.textContent === "N/A") {
                var graminoids_query_three = firebase.database().ref("speciesid/field_guide/graminoids/poaceae").orderByKey();
                graminoids_query_three.once("value")
                    .then(function(snapshot) {
                        snapshot.forEach(function(childSnapshot) {

                            /* Graminoids - Poaceae */
                            var speciesName = childSnapshot.val().species_name;
                            var commonName = childSnapshot.val().common_name;
                            var familyName = childSnapshot.val().family_name;
                            var plantcode = childSnapshot.val().plant_code;
                            var notesName = childSnapshot.val().notes;
                            var photoCreditName = childSnapshot.val().photo_credit;
                            var synonymsName = childSnapshot.val().synonyms;
                            var growthFormName = childSnapshot.val().growth_form;
                            var awnsName = childSnapshot.val().awns;
                            var floretsPerSpikeletName = childSnapshot.val().florets_per_spikelet;
                            var inflorescenceName = childSnapshot.val().inflorescence;
                            var leafBladeName = childSnapshot.val().leaf_blade;
                            var stemCrossSectionName = childSnapshot.val().stem_cross_section;
                            var habitatName = childSnapshot.val().habitat;
                            var ryanmidigatearray = [familyName, growthFormName, stemCrossSectionName, awnsName, floretsPerSpikeletName, inflorescenceName, '63']
                            var habitatNameArray = habitatName.split(',')
                            var leafBladeNameArray = leafBladeName.split(',')
                            var awnsNameArray = awnsName.split(',')
                            var testarray = [];
                            var habitatNameArraylen = habitatNameArray.length;
                            var lbna = leafBladeNameArray.length
                            var ana = awnsNameArray.length
                                // same as forbs
                            for (var t = 0; t < habitatNameArraylen; t++) {
                                testarray[t] = habitatNameArray[t].trim()
                            }

                            ryanmidigatearray = ryanmidigatearray.concat(testarray)

                            for (var t = 0; t < lbna; t++) {
                                testarray[t] = leafBladeNameArray[t].trim()
                            }

                            ryanmidigatearray = ryanmidigatearray.concat(testarray)
                            for (var t = 0; t < ana; t++) {
                                testarray[t] = awnsNameArray[t].trim()
                            }

                            ryanmidigatearray = ryanmidigatearray.concat(testarray)
                            var valid = true;
                            for (var i = 0; i < the_length; i++) {
                                var filtname = par_subcat_array[i]
                                var filtprop = par_fil_array[i]
                                if (ryanmidigatearray.indexOf(filtprop) != -1) {
                                    continue
                                } else {
                                    valid = false;

                                }

                            }
                            if (valid) {
                                var h6 = document.createElement('h6');
                                var text = document.createTextNode(speciesName);
                                h6.appendChild(text);
                                document.body.appendChild(h6);

                                h6.onclick = function() {
                                    dis_plant_code.textContent = plantcode;
                                    display_species.textContent = speciesName;
                                    display_common_name.textContent = commonName;
                                    display_family_name.textContent = familyName;
                                    display_synonyms.textContent = synonymsName;
                                    display_growth_form.textContent = growthFormName;
                                    display_awns.textContent = awnsName;
                                    display_florets_per_spikelet.textContent = floretsPerSpikeletName;
                                    display_inflorescence.textContent = inflorescenceName;
                                    display_leaf_blade.textContent = leafBladeName;
                                    display_habitat.textContent = habitatName;
                                    display_stem_cross_section.textContent = stemCrossSectionName;
                                    display_notes.textContent = notesName;
                                    display_photo_credit.textContent = photoCreditName;

                                };

                                var h5 = document.createElement('h5');
                                var text2 = document.createTextNode(commonName);
                                h5.appendChild(text2);
                                document.body.appendChild(h5);

                            }
                        });

                    });
            }
        }
        if (par_category.textContent === "Woody" || par_category.textContent === "N/A") {
            if (par_cat2.textContent === "deciduous" || par_cat2.textContent === "N/A") {
                var woody_query_one = firebase.database().ref("speciesid/field_guide/woody/deciduous").orderByKey();
                woody_query_one.once("value")
                    .then(function(snapshot) {
                        snapshot.forEach(function(childSnapshot) {

                            /* Woodys - deciduous */
                            var speciesName = childSnapshot.val().species_name;
                            var commonName = childSnapshot.val().common_name;
                            var familyName = childSnapshot.val().family_name;
                            var plantcode = childSnapshot.val().plant_code;
                            var notesName = childSnapshot.val().notes;
                            var photoCreditName = childSnapshot.val().photo_credit;
                            var synonymsName = childSnapshot.val().synonyms;
                            var growthFormName = childSnapshot.val().growth_form;
                            var coneName = childSnapshot.val().cone;
                            var leafArrangementName = childSnapshot.val().leaf_arrangement;
                            var leafMarginName = childSnapshot.val().leaf_margin;
                            var leafShapeName = childSnapshot.val().leaf_shape;
                            var leafTypeName = childSnapshot.val().leaf_type;
                            var ryanmidigatearray = [familyName, growthFormName, leafMarginName, leafShapeName, leafTypeName, leafArrangementName, '63']
                            var valid = true;
                            // same as forbs

                            for (var i = 0; i < the_length; i++) {
                                var filtname = par_subcat_array[i]
                                var filtprop = par_fil_array[i]
                                if (ryanmidigatearray.indexOf(filtprop) != -1) {
                                    continue
                                } else {
                                    valid = false;

                                }

                            }
                            if (valid) {
                                var h6 = document.createElement('h6');
                                var text = document.createTextNode(speciesName);
                                h6.appendChild(text);
                                document.body.appendChild(h6);

                                h6.onclick = function() {
                                    dis_plant_code.textContent = plantcode;
                                    display_species.textContent = speciesName;
                                    display_common_name.textContent = commonName;
                                    display_family_name.textContent = familyName;
                                    display_synonyms.textContent = synonymsName;
                                    display_growth_form.textContent = growthFormName;
                                    display_cone.textContent = coneName;
                                    display_leaf_arrangement.textContent = leafArrangementName;
                                    display_leaf_margin.textContent = leafMarginName;
                                    display_leaf_shape.textContent = leafShapeName;
                                    display_leaf_type.textContent = leafTypeName;
                                    display_notes.textContent = notesName;
                                    display_photo_credit.textContent = photoCreditName;

                                };

                                var h5 = document.createElement('h5');
                                var text2 = document.createTextNode(commonName);
                                h5.appendChild(text2);
                                document.body.appendChild(h5);

                            }
                        });

                    });
            }
            if (par_cat2.textContent === "needle" || par_cat2.textContent === "N/A") {
                var woody_query_two = firebase.database().ref("speciesid/field_guide/woody/needle").orderByKey();
                woody_query_two.once("value")
                    .then(function(snapshot) {
                        snapshot.forEach(function(childSnapshot) {

                            /* Woodys - Needle */
                            var speciesName = childSnapshot.val().species_name;
                            var commonName = childSnapshot.val().common_name;
                            var plantcode = childSnapshot.val().plant_code;
                            var familyName = childSnapshot.val().family_name;
                            var notesName = childSnapshot.val().notes;
                            var photoCreditName = childSnapshot.val().photo_credit;
                            var synonymsName = childSnapshot.val().synonyms;
                            var growthFormName = childSnapshot.val().growth_form;
                            var coneName = childSnapshot.val().cone;
                            var needleApexName = childSnapshot.val().needle_apex;
                            var needleArrangementName = childSnapshot.val().needle_arrangement;
                            var needlePerFascileName = childSnapshot.val().needle_per_fascile;
                            var leafTypeName = childSnapshot.val().leaf_type;
                            var ryanmidigatearray = [familyName, growthFormName, needleApexName, needleArrangementName, leafTypeName, needlePerFascileName, '63']
                            var valid = true;
                            var testarray = []
                            var coneNameArray = coneName.split(',')
                            var cna = coneNameArray.length
                                // same as forbs 
                            for (var t = 0; t < cna; t++) {
                                testarray[t] = coneNameArray[t].trim()
                            }

                            ryanmidigatearray = ryanmidigatearray.concat(testarray)

                            for (var i = 0; i < the_length; i++) {
                                var filtname = par_subcat_array[i]
                                var filtprop = par_fil_array[i]
                                if (ryanmidigatearray.indexOf(filtprop) != -1) {
                                    continue
                                } else {
                                    valid = false;
                                    break
                                }

                            }
                            if (valid) {
                                var h6 = document.createElement('h6');
                                var text = document.createTextNode(speciesName);
                                h6.appendChild(text);
                                document.body.appendChild(h6);

                                h6.onclick = function() {
                                    dis_plant_code.textContent = plantcode;
                                    display_species.textContent = speciesName;
                                    display_common_name.textContent = commonName;
                                    display_family_name.textContent = familyName;
                                    display_synonyms.textContent = synonymsName;
                                    display_growth_form.textContent = growthFormName;
                                    display_cone.textContent = coneName;
                                    display_needle_apex.textContent = needleApexName;
                                    display_needle_arrangement.textContent = needleArrangementName;
                                    display_needle_per_fascile.textContent = needlePerFascileName;
                                    display_leaf_type.textContent = leafTypeName;
                                    display_notes.textContent = notesName;
                                    display_photo_credit.textContent = photoCreditName;

                                };

                                var h5 = document.createElement('h5');
                                var text2 = document.createTextNode(commonName);
                                h5.appendChild(text2);
                                document.body.appendChild(h5);

                            }
                        });

                    });
            }
        }
    }

}, 500);