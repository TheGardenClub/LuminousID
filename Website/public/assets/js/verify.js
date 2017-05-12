(function() {

  // Get elements

  const show_verify_text = document.getElementById('show_verify_text');
  const verify_text = document.getElementById('verify_text');
  const email_login_text = document.getElementById('email_login_text');
  const password_login_text = document.getElementById('password_login_text');
  const email_of_user_text = document.getElementById('email_of_user_text');
  const email_verify_text = document.getElementById('email_verify_text');
  const submit_btn = document.getElementById('submit_btn');

  // Firebase 
  const auth = firebase.auth();

  // Add login event
  submit_btn.addEventListener('click', e => {

    // get email and pass
    const email_login = email_login_text.value;
  	const pass = password_login_text.value;

    const verify_email = email_verify_text.value;

    // TODO: Can't verify yourself as a reseacher DONE

    if (email_login == verify_email) {

      alert("You can't verify yourself");

    } else {

      // Sign in
      const promise = auth.signInWithEmailAndPassword(email_login, pass);
      promise.catch(e => alert(e.message));

      // TODO: check if your that logs in is a researcher DONE

      var update_reseacher = firebase.database().ref("speciesid/accounts/");

      update_reseacher.once("value").then(function(snapshot) {
        snapshot.forEach(function(childSnapshot) {

          var find_email = childSnapshot.val().email;

          if (find_email == email_login) {

            var researcher_val = childSnapshot.val().researcher;

            if (researcher_val >= 3) {

              firebase.auth().onAuthStateChanged(firebaseUser => {

                if(firebaseUser){

                  var update_reseacher = firebase.database().ref("speciesid/accounts/");

                  update_reseacher.once("value").then(function(snapshot) {
                    snapshot.forEach(function(childSnapshot) {

                      var find_email = childSnapshot.val().email;

                      if (find_email == verify_email) {

                        var researcher_val = childSnapshot.val().researcher;
                        var user_uid = childSnapshot.getKey();

                        researcher_val = researcher_val + 1;

                        firebase.database().ref("speciesid/accounts").child(user_uid).update({
                          "researcher": researcher_val
                        });
                      }

                    });

                  });

                  show_verify_text.classList.remove('hide');

                  verify_text.classList.add('hide');
                  email_login_text.classList.add('hide');
                  password_login_text.classList.add('hide');
                  email_of_user_text.classList.add('hide');
                  email_verify_text.classList.add('hide');
                  submit_btn.classList.add('hide');

                  firebase.auth().signOut();

                }

              });

            } else {

              alert("This user is not a researcher");

            }

          }

        });

      });

    }

  });

}());
