(function() {


    document.body.style.backgroundColor = "#384452";
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

                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {

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

                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {

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

                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {

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

                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {

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

                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {

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

                var h6 = document.createElement('h6');
                var text = document.createTextNode(speciesName);
                h6.appendChild(text);
                document.body.appendChild(h6);

                h6.onclick = function() {

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