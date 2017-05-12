(function() {

    document.body.style.backgroundColor = "#384452";

    var storage = firebase.storage();
    var storageRef = storage.ref();
    var tangRef = storageRef.child('field_guide/forbs_photos/ACHMIL_1.jpg');

    tangRef.getDownloadURL().then(function(url) {
        document.querySelector('img').src = url;
    })


}());

setInterval(function() {

    var display_species2 = document.getElementById("display_species");
    var display_common_name2 = document.getElementById("display_common_name");
    var display_family_name2 = document.getElementById("display_family_name");
    var display_synonyms2 = document.getElementById("display_synonyms");
    var display_growth_form2 = document.getElementById("display_growth_form");
    var display_flower_color2 = document.getElementById("display_flower_color");
    var display_flower_shape2 = document.getElementById("display_flower_shape");
    var display_habitat2 = document.getElementById("display_habitat");
    var display_leaf_arrangement2 = document.getElementById("display_leaf_arrangement");
    var display_leaf_shape_filter2 = document.getElementById("display_leaf_shape_filter");
    var display_petal_number2 = document.getElementById("display_petal_number");
    var display_notes2 = document.getElementById("display_notes");
    var display_photo_credit2 = document.getElementById("display_photo_credit");
    var display_inflorescence2 = document.getElementById("display_inflorescence");
    var display_leaf_blade2 = document.getElementById("display_leaf_blade");
    var display_spike_color2 = document.getElementById("display_spike_color");
    var display_stem_cross_section2 = document.getElementById("display_stem_cross_section");
    var display_awns2 = document.getElementById("display_awns");
    var display_florets_per_spikelet2 = document.getElementById("display_florets_per_spikelet");
    var display_cone2 = document.getElementById("display_cone");
    var display_leaf_margin2 = document.getElementById("display_leaf_margin");
    var display_leaf_shape2 = document.getElementById("display_leaf_shape");
    var display_leaf_type2 = document.getElementById("display_leaf_type");
    var display_needle_apex2 = document.getElementById("display_needle_apex");
    var display_needle_arrangement2 = document.getElementById("display_needle_arrangement");
    var display_needle_per_fascile2 = document.getElementById("display_needle_per_fascile");

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

    if (display_species2.textContent === display_species.innerHTML) {} else {

        /* ALL */
        display_species2.textContent = display_species.innerHTML;
        display_common_name2.textContent = display_common_name.innerHTML;
        display_family_name2.textContent = display_family_name.innerHTML;
        display_synonyms2.textContent = display_synonyms.innerHTML;
        display_notes2.textContent = display_notes.innerHTML;
        display_photo_credit2.textContent = display_photo_credit.innerHTML;
        display_growth_form2.textContent = display_growth_form.innerHTML;
        display_leaf_type2.textContent = display_leaf_type.innerHTML;
        display_flower_color2.textContent = display_flower_color.innerHTML;
        display_flower_shape2.textContent = display_flower_shape.innerHTML;
        display_habitat2.textContent = display_habitat.innerHTML;
        display_leaf_arrangement2.textContent = display_leaf_arrangement.innerHTML;
        display_leaf_shape_filter2.textContent = display_leaf_shape_filter.innerHTML;
        display_petal_number2.textContent = display_petal_number.innerHTML;
        display_inflorescence2.textContent = display_inflorescence.innerHTML;
        display_leaf_blade2.textContent = display_leaf_blade.innerHTML;
        display_spike_color2.textContent = display_spike_color.innerHTML;
        display_stem_cross_section2.textContent = display_stem_cross_section.innerHTML;
        display_awns2.textContent = display_awns.innerHTML;
        display_florets_per_spikelet2.textContent = display_florets_per_spikelet.innerHTML;
        display_cone2.textContent = display_cone.innerHTML;
        display_leaf_margin2.textContent = display_leaf_margin.innerHTML;
        display_leaf_shape2.textContent = display_leaf_shape.innerHTML;
        display_leaf_type2.textContent = display_leaf_type.innerHTML;
        display_needle_apex2.textContent = display_needle_apex.innerHTML;
        display_needle_arrangement2.textContent = display_needle_arrangement.innerHTML;
        display_needle_per_fascile2.textContent = display_needle_per_fascile.innerHTML;

        if (display_growth_form2.textContent == 'forb') {

            /* Forbs Show */
            var storage = firebase.storage();
            var storageRef = storage.ref();
            var plant_code = parent.document.getElementById("plant_code").innerHTML
            var tangRef = storageRef.child('field_guide/forbs_photos/' + plant_code + '_1.jpg');

            tangRef.getDownloadURL().then(function(url) {
                document.querySelector('img').src = url;
            })
            document.getElementById('flower_color_div').style.display = 'block';
            document.getElementById('flower_shape_div').style.display = 'block';
            document.getElementById('leaf_arrangement_div').style.display = 'block';
            document.getElementById('leaf_shape_filter_div').style.display = 'block';
            document.getElementById('petal_number_div').style.display = 'block';
            document.getElementById('habitat_div').style.display = 'block';

            /* Forbs No Show*/
            document.getElementById('inflorescence_div').style.display = 'none';
            document.getElementById('leaf_blade_div').style.display = 'none';
            document.getElementById('spike_color_div').style.display = 'none';
            document.getElementById('stem_cross_section_div').style.display = 'none';
            document.getElementById('awns_div').style.display = 'none';
            document.getElementById('florets_per_spikelet_div').style.display = 'none';
            document.getElementById('cone_div').style.display = 'none';
            document.getElementById('leaf_margin_div').style.display = 'none';
            document.getElementById('leaf_shape_div').style.display = 'none';
            document.getElementById('leaf_type_div').style.display = 'none';
            document.getElementById('needle_apex_div').style.display = 'none';
            document.getElementById('needle_arrangement_div').style.display = 'none';
            document.getElementById('needle_per_fascile_div').style.display = 'none';

            display_leaf_type2.textContent = '';

        };

        if (display_family_name2.textContent == 'Cyperaceae (Sedges)') {

            /* Graminoids (Cyperaceae) Show */
            var storage = firebase.storage();
            var storageRef = storage.ref();
            var plant_code = parent.document.getElementById("plant_code").innerHTML
            var tangRef = storageRef.child('field_guide/grams_photos/' + plant_code + '_1.jpg');

            tangRef.getDownloadURL().then(function(url) {
                document.querySelector('img').src = url;
            })
            document.getElementById('inflorescence_div').style.display = 'block';
            document.getElementById('display_inflorescence').style.display = 'block';
            document.getElementById('display_inflorescence_main').style.display = 'block';

            document.getElementById('leaf_blade_div').style.display = 'block';
            document.getElementById('display_leaf_blade').style.display = 'block';
            document.getElementById('display_leaf_blade_main').style.display = 'block';

            document.getElementById('spike_color_div').style.display = 'block';
            document.getElementById('display_spike_color').style.display = 'block';
            document.getElementById('display_spike_color_main').style.display = 'block';

            document.getElementById('stem_cross_section_div').style.display = 'block';
            document.getElementById('display_stem_cross_section').style.display = 'block';
            document.getElementById('display_stem_cross_section_main').style.display = 'block';

            document.getElementById('habitat_div').style.display = 'block';
            document.getElementById('display_habitat').style.display = 'block';
            document.getElementById('display_habitat_main').style.display = 'block';

            /* Graminoids (Cyperaceae) No Show*/
            document.getElementById('flower_color_div').style.display = 'none';
            document.getElementById('flower_shape_div').style.display = 'none';
            document.getElementById('leaf_arrangement_div').style.display = 'none';
            document.getElementById('leaf_shape_filter_div').style.display = 'none';
            document.getElementById('petal_number_div').style.display = 'none';
            document.getElementById('awns_div').style.display = 'none';
            document.getElementById('florets_per_spikelet_div').style.display = 'none';
            document.getElementById('cone_div').style.display = 'none';
            document.getElementById('leaf_margin_div').style.display = 'none';
            document.getElementById('leaf_shape_div').style.display = 'none';
            document.getElementById('leaf_type_div').style.display = 'none';
            document.getElementById('needle_apex_div').style.display = 'none';
            document.getElementById('needle_arrangement_div').style.display = 'none';
            document.getElementById('needle_per_fascile_div').style.display = 'none';

            display_leaf_type2.textContent = '';

        };

        if (display_family_name2.textContent == 'Juncaceae (Rushes)') {

            /* Graminoids (Juncaceae) Show */
            var storage = firebase.storage();
            var storageRef = storage.ref();
            var plant_code = parent.document.getElementById("plant_code").innerHTML
            var tangRef = storageRef.child('field_guide/grams_photos/' + plant_code + '_1.jpg');

            tangRef.getDownloadURL().then(function(url) {
                document.querySelector('img').src = url;
            })
            document.getElementById('leaf_blade_div').style.display = 'block';
            document.getElementById('display_leaf_blade').style.display = 'block';
            document.getElementById('display_leaf_blade_main').style.display = 'block';

            document.getElementById('stem_cross_section_div').style.display = 'block';
            document.getElementById('display_stem_cross_section').style.display = 'block';
            document.getElementById('display_stem_cross_section_main').style.display = 'block';

            document.getElementById('habitat_div').style.display = 'block';
            document.getElementById('display_habitat').style.display = 'block';
            document.getElementById('display_habitat_main').style.display = 'block';

            /* Graminoids (Juncaceae) No Show */
            document.getElementById('spike_color_div').style.display = 'none';
            document.getElementById('inflorescence_div').style.display = 'none';
            document.getElementById('flower_color_div').style.display = 'none';
            document.getElementById('flower_shape_div').style.display = 'none';
            document.getElementById('leaf_arrangement_div').style.display = 'none';
            document.getElementById('leaf_shape_filter_div').style.display = 'none';
            document.getElementById('petal_number_div').style.display = 'none';
            document.getElementById('awns_div').style.display = 'none';
            document.getElementById('florets_per_spikelet_div').style.display = 'none';
            document.getElementById('cone_div').style.display = 'none';
            document.getElementById('leaf_margin_div').style.display = 'none';
            document.getElementById('leaf_shape_div').style.display = 'none';
            document.getElementById('leaf_type_div').style.display = 'none';
            document.getElementById('needle_apex_div').style.display = 'none';
            document.getElementById('needle_arrangement_div').style.display = 'none';
            document.getElementById('needle_per_fascile_div').style.display = 'none';

            display_leaf_type2.textContent = '';

        };

        if (display_family_name2.textContent == 'Poaceae (grasses)') {

            /* Graminoids (Poaceae) Show */
            var storage = firebase.storage();
            var storageRef = storage.ref();
            var plant_code = parent.document.getElementById("plant_code").innerHTML
            var tangRef = storageRef.child('field_guide/grams_photos/' + plant_code + '_1.jpg');

            tangRef.getDownloadURL().then(function(url) {
                document.querySelector('img').src = url;
            })
            document.getElementById('awns_div').style.display = 'block';
            document.getElementById('display_awns').style.display = 'block';
            document.getElementById('display_awns_main').style.display = 'block';

            document.getElementById('florets_per_spikelet_div').style.display = 'block';
            document.getElementById('display_florets_per_spikelet').style.display = 'block';
            document.getElementById('display_florets_per_spikelet_main').style.display = 'block';

            document.getElementById('inflorescence_div').style.display = 'block';
            document.getElementById('display_inflorescence').style.display = 'block';
            document.getElementById('display_inflorescence_main').style.display = 'block';

            document.getElementById('leaf_blade_div').style.display = 'block';
            document.getElementById('display_leaf_blade').style.display = 'block';
            document.getElementById('display_leaf_blade_main').style.display = 'block';

            document.getElementById('stem_cross_section_div').style.display = 'block';
            document.getElementById('display_stem_cross_section').style.display = 'block';
            document.getElementById('display_stem_cross_section_main').style.display = 'block';

            document.getElementById('habitat_div').style.display = 'block';
            document.getElementById('display_habitat').style.display = 'block';
            document.getElementById('display_habitat_main').style.display = 'block';

            /* Graminoids (Poaceae) No Show */
            document.getElementById('spike_color_div').style.display = 'none';
            document.getElementById('flower_color_div').style.display = 'none';
            document.getElementById('flower_shape_div').style.display = 'none';
            document.getElementById('leaf_arrangement_div').style.display = 'none';
            document.getElementById('leaf_shape_filter_div').style.display = 'none';
            document.getElementById('petal_number_div').style.display = 'none';
            document.getElementById('cone_div').style.display = 'none';
            document.getElementById('leaf_margin_div').style.display = 'none';
            document.getElementById('leaf_shape_div').style.display = 'none';
            document.getElementById('leaf_type_div').style.display = 'none';
            document.getElementById('needle_apex_div').style.display = 'none';
            document.getElementById('needle_arrangement_div').style.display = 'none';
            document.getElementById('needle_per_fascile_div').style.display = 'none';

            display_leaf_type2.textContent = '';

        };

        if (display_leaf_type2.textContent == 'deciduous') {

            /* Woodys (Deciduous) Show */
            var storage = firebase.storage();
            var storageRef = storage.ref();
            var plant_code = parent.document.getElementById("plant_code").innerHTML
            var tangRef = storageRef.child('field_guide/tree_shrubs_photos/' + plant_code + '.jpg');

            tangRef.getDownloadURL().then(function(url) {
                document.querySelector('img').src = url;
            })
            document.getElementById('cone_div').style.display = 'block';
            document.getElementById('display_cone').style.display = 'block';
            document.getElementById('display_cone_main').style.display = 'block';

            document.getElementById('leaf_arrangement_div').style.display = 'block';
            document.getElementById('display_leaf_arrangement').style.display = 'block';
            document.getElementById('display_leaf_arrangement_main').style.display = 'block';

            document.getElementById('leaf_margin_div').style.display = 'block';
            document.getElementById('display_leaf_margin').style.display = 'block';
            document.getElementById('display_leaf_margin_main').style.display = 'block';

            document.getElementById('leaf_shape_div').style.display = 'block';
            document.getElementById('display_leaf_shape').style.display = 'block';
            document.getElementById('display_leaf_shape_main').style.display = 'block';

            document.getElementById('leaf_type_div').style.display = 'block';
            document.getElementById('display_leaf_type').style.display = 'block';
            document.getElementById('display_leaf_type_main').style.display = 'block';

            /* Woodys (Deciduous) No Show */
            document.getElementById('spike_color_div').style.display = 'none';
            document.getElementById('flower_color_div').style.display = 'none';
            document.getElementById('flower_shape_div').style.display = 'none';
            document.getElementById('awns_div').style.display = 'none';
            document.getElementById('florets_per_spikelet_div').style.display = 'none';
            document.getElementById('inflorescence_div').style.display = 'none';
            document.getElementById('leaf_blade_div').style.display = 'none';
            document.getElementById('stem_cross_section_div').style.display = 'none';
            document.getElementById('habitat_div').style.display = 'none';
            document.getElementById('leaf_shape_filter_div').style.display = 'none';
            document.getElementById('petal_number_div').style.display = 'none';
            document.getElementById('needle_apex_div').style.display = 'none';
            document.getElementById('needle_arrangement_div').style.display = 'none';
            document.getElementById('needle_per_fascile_div').style.display = 'none';

        };

        if (display_leaf_type2.textContent == 'needle') {

            /* Woodys (Needle) Show */
            var storage = firebase.storage();
            var storageRef = storage.ref();
            var plant_code = parent.document.getElementById("plant_code").innerHTML
            var tangRef = storageRef.child('field_guide/tree_shrubs_photos/' + plant_code + '.jpg');

            tangRef.getDownloadURL().then(function(url) {
                document.querySelector('img').src = url;
            })
            document.getElementById('cone_div').style.display = 'block';
            document.getElementById('display_cone').style.display = 'block';
            document.getElementById('display_cone_main').style.display = 'block';

            document.getElementById('needle_apex_div').style.display = 'block';
            document.getElementById('display_needle_apex').style.display = 'block';
            document.getElementById('display_needle_apex_main').style.display = 'block';

            document.getElementById('needle_arrangement_div').style.display = 'block';
            document.getElementById('display_needle_arrangement').style.display = 'block';
            document.getElementById('display_needle_arrangement_main').style.display = 'block';

            document.getElementById('needle_per_fascile_div').style.display = 'block';
            document.getElementById('display_needle_per_fascile').style.display = 'block';
            document.getElementById('display_needle_per_fascile_main').style.display = 'block';

            document.getElementById('leaf_type_div').style.display = 'block';


            /* Woodys (Needle) No Show */
            document.getElementById('spike_color_div').style.display = 'none';
            document.getElementById('flower_color_div').style.display = 'none';
            document.getElementById('flower_shape_div').style.display = 'none';
            document.getElementById('awns_div').style.display = 'none';
            document.getElementById('florets_per_spikelet_div').style.display = 'none';
            document.getElementById('inflorescence_div').style.display = 'none';
            document.getElementById('leaf_blade_div').style.display = 'none';
            document.getElementById('stem_cross_section_div').style.display = 'none';
            document.getElementById('habitat_div').style.display = 'none';
            document.getElementById('leaf_shape_filter_div').style.display = 'none';
            document.getElementById('petal_number_div').style.display = 'none';
            document.getElementById('leaf_arrangement_div').style.display = 'none';
            document.getElementById('leaf_margin_div').style.display = 'none';
            document.getElementById('leaf_shape_div').style.display = 'none';

        };

    }

}, 50);