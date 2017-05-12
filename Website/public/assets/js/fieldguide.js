/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
*/
(function() {


    // Get elements
    const txtSearch = document.getElementById('txtSearch');
    const btnSearch = document.getElementById('btnSearch');
    const btnFilter = document.getElementById('btnFilter');
    const btnDownload = document.getElementById('btnDownload');

    // Firebase 
    const database = firebase.database(); // root of database
    const storage = firebase.storage();
    // Add login event
    $(document).ready(function() {
        $('.dropdown-submenu a.test').on("click", function(e) {
            $(this).next('ul').toggle();
            e.stopPropagation();
            e.preventDefault();
        });
    });

}());