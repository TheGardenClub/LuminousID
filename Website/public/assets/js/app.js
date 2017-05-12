(function() {

  // Get elements
  const check_mark = document.getElementById("check_mark");
  const check_mark2 = document.getElementById("check_mark2");

  const verify_researcher_text = document.getElementById('verify_researcher_text');
  const verify_researcher_input = document.getElementById('verify_researcher_input');
  const verify_researcher_submit = document.getElementById('verify_researcher_submit');
  const verify_researcher_quit = document.getElementById('verify_researcher_quit');

  const reset_password_text = document.getElementById('reset_password_text');
  const reset_password_yes = document.getElementById('reset_password_yes');
  const reset_password_no = document.getElementById('reset_password_no');

  const forgot_password_text = document.getElementById('forgot_password_text');
  const forgot_password_input = document.getElementById('forgot_password_input');
  const forgot_password_submit = document.getElementById('forgot_password_submit');
  const forgot_password_quit = document.getElementById('forgot_password_quit');

  const have_account_txt = document.getElementById('have_account_txt');
  const txtEmail_login = document.getElementById('txtEmail_login');
  const txtPassword_login = document.getElementById('txtPassword_login');
  const btnLogin = document.getElementById('btnLogin');
  const forgot_password_btn = document.getElementById('forgot_password_btn');

  const dont_have_account_txt = document.getElementById('dont_have_account_txt');
  const txtUsername_create = document.getElementById('txtUsername_create');
  const txtEmail_create = document.getElementById('txtEmail_create');
  const txtPassword_create = document.getElementById('txtPassword_create');
  const txtPassword_create_2 = document.getElementById('txtPassword_create_2');
  const btnSignUp = document.getElementById('btnSignUp');

  const user_email = document.getElementById('user_email');
  const user_status = document.getElementById('user_status');
  const welcome_txt = document.getElementById('welcome_txt');
  const reset_password_txt = document.getElementById('reset_password_txt');
  const verify_researcher_btn = document.getElementById('verify_researcher_btn');
  const btnLogout = document.getElementById('btnLogout');
  const recent_observations = document.getElementById('recent_observations');
  const no_observations = document.getElementById('no_observations');

  // Firebase 
  const auth = firebase.auth();
  const database = firebase.database(); // root of database
  const storage = firebase.storage();

  // Add login event
  btnLogin.addEventListener('click', e => {

    // get email and pass
    const email = txtEmail_login.value;
  	const pass = txtPassword_login.value;

  	// Sign in
  	const promise = auth.signInWithEmailAndPassword(email, pass);
  	promise.catch(e => alert(e.message));

  });

  // Add logout event
  btnLogout.addEventListener('click', e => { 
    firebase.auth().signOut();
  });

  // Add signup event
  btnSignUp.addEventListener('click', e => {

    //const uid = firebaseUser.uid; // get user uid

    // get email and pass
    const email = txtEmail_create.value;
    const user = txtUsername_create.value;
    const pass = txtPassword_create.value;
    const pass_2 = txtPassword_create_2.value;

    if (pass != pass_2) {

      alert("Your passwords don't match");

    } else {

      const promise = auth.createUserWithEmailAndPassword(email, pass).then(function(response) {
        //console.log(response.uid);
        firebase.database().ref("speciesid/accounts").child(response.uid).set({
            "researcher": 0.0,
            "username": user,
            "email": email
        });

        promise.catch(e => alert(e.message));

      });
    }
  });

  // Add reset password
  verify_researcher_btn.addEventListener('click', e => {

    // TODO: Check if already Resaercher DONE

    const user_uid = firebase.auth().currentUser.uid;

    var check_reseacher = firebase.database().ref("speciesid/accounts/").child(user_uid);

    check_reseacher.once("value").then(function(snapshot) {

      var get_researcher_val = snapshot.val().researcher;

      if (get_researcher_val >= 3) {

        alert("You are already a researcher");

      } else {

        verify_researcher_text.classList.remove('hide');
        verify_researcher_input.classList.remove('hide');
        verify_researcher_submit.classList.remove('hide');
        verify_researcher_quit.classList.remove('hide');


        reset_password_txt.classList.add('hide');
        verify_researcher_btn.classList.add('hide');
        btnLogout.classList.add('hide');
        recent_observations.classList.add('hide');
        no_observations.classList.add('hide');

      }

    });

  });

  // Add reset password
  verify_researcher_submit.addEventListener('click', e => {

    const email = verify_researcher_input.value;
    const verify_email = firebase.auth().currentUser.email;

    // TODO: Check that user is sending reseacher verification to themself DONE

    if (email == verify_email) {

      alert("You can't verify yourself");

    } else {

      // TODO: Check if person sending email to is a reseacher DONE

      var update_reseacher = firebase.database().ref("speciesid/accounts/");

      update_reseacher.once("value").then(function(snapshot) {
        snapshot.forEach(function(childSnapshot) {

          var find_email = childSnapshot.val().email;

          if (find_email == email) {

            var researcher_val = childSnapshot.val().researcher;

            if (researcher_val >= 3) {

              emailjs.send("mailjet", "verify_researcher", {"link":"https://luminousid.org/verify.html","email":email,"user_email":verify_email})

              verify_researcher_text.classList.add('hide');
              verify_researcher_input.classList.add('hide');
              verify_researcher_submit.classList.add('hide');
              verify_researcher_quit.classList.add('hide');

              welcome_txt.classList.remove('hide');
              user_email.classList.remove('hide');
              user_status.classList.remove('hide');
              reset_password_txt.classList.remove('hide');
              verify_researcher_btn.classList.remove('hide');
              btnLogout.classList.remove('hide');
              recent_observations.classList.remove('hide');
              no_observations.classList.remove('hide');

              alert("Verification sent");

            } else {

              alert("This user is not a researcher");

            }

          }

        });

      });

    }

  });

  verify_researcher_quit.addEventListener('click', e => {

    verify_researcher_text.classList.add('hide');
    verify_researcher_input.classList.add('hide');
    verify_researcher_submit.classList.add('hide');
    verify_researcher_quit.classList.add('hide');

    welcome_txt.classList.remove('hide');
    user_email.classList.remove('hide');
    user_status.classList.remove('hide');
    reset_password_txt.classList.remove('hide');
    verify_researcher_btn.classList.remove('hide');
    btnLogout.classList.remove('hide');
    recent_observations.classList.remove('hide');
    no_observations.classList.remove('hide');

  });

  reset_password_no.addEventListener('click', e => {

    reset_password_text.classList.add('hide');
    reset_password_yes.classList.add('hide');
    reset_password_no.classList.add('hide');

    welcome_txt.classList.remove('hide');
    user_email.classList.remove('hide');
    user_status.classList.remove('hide');
    reset_password_txt.classList.remove('hide');
    verify_researcher_btn.classList.remove('hide');
    btnLogout.classList.remove('hide');
    recent_observations.classList.remove('hide');
    no_observations.classList.remove('hide');

  });

  reset_password_yes.addEventListener('click', e => {

    const email = firebase.auth().currentUser.email;
    auth.sendPasswordResetEmail(email);

    alert("Please check your email");

    reset_password_text.classList.add('hide');
    reset_password_yes.classList.add('hide');
    reset_password_no.classList.add('hide');

    welcome_txt.classList.remove('hide');
    user_email.classList.remove('hide');
    user_status.classList.remove('hide');
    reset_password_txt.classList.remove('hide');
    verify_researcher_btn.classList.remove('hide');
    btnLogout.classList.remove('hide');
    recent_observations.classList.remove('hide');
    no_observations.classList.remove('hide');

  });

    // Add reset password
  reset_password_txt.addEventListener('click', e => {

    reset_password_text.classList.remove('hide');
    reset_password_yes.classList.remove('hide');
    reset_password_no.classList.remove('hide');


    reset_password_txt.classList.add('hide');
    verify_researcher_btn.classList.add('hide');
    btnLogout.classList.add('hide');
    recent_observations.classList.add('hide');
    no_observations.classList.add('hide');

  });

  // Add reset password
  forgot_password_submit.addEventListener('click', e => {

    const email = forgot_password_input.value;
    const promise = auth.sendPasswordResetEmail(email);

    // TODO: Catch error when typing if email
    //promise.catch(e => alert(e.message));

    alert("Please check your email");

    forgot_password_text.classList.add('hide');
    forgot_password_input.classList.add('hide');
    forgot_password_submit.classList.add('hide');
    forgot_password_quit.classList.add('hide');

    have_account_txt.classList.remove('hide');
    txtEmail_login.classList.remove('hide');
    txtPassword_login.classList.remove('hide');
    btnLogin.classList.remove('hide');
    forgot_password_btn.classList.remove('hide');

    dont_have_account_txt.classList.remove('hide');
    txtUsername_create.classList.remove('hide');
    txtEmail_create.classList.remove('hide');
    txtPassword_create.classList.remove('hide');
    txtPassword_create_2.classList.remove('hide');
    btnSignUp.classList.remove('hide');


  });

  forgot_password_quit.addEventListener('click', e => {

    forgot_password_text.classList.add('hide');
    forgot_password_input.classList.add('hide');
    forgot_password_submit.classList.add('hide');
    forgot_password_quit.classList.add('hide');

    have_account_txt.classList.remove('hide');
    txtEmail_login.classList.remove('hide');
    txtPassword_login.classList.remove('hide');
    btnLogin.classList.remove('hide');
    forgot_password_btn.classList.remove('hide');

    dont_have_account_txt.classList.remove('hide');
    txtUsername_create.classList.remove('hide');
    txtEmail_create.classList.remove('hide');
    txtPassword_create.classList.remove('hide');
    txtPassword_create_2.classList.remove('hide');
    btnSignUp.classList.remove('hide');


  });

    // Add reset password
  forgot_password_btn.addEventListener('click', e => {

    forgot_password_text.classList.remove('hide');
    forgot_password_input.classList.remove('hide');
    forgot_password_submit.classList.remove('hide');
    forgot_password_quit.classList.remove('hide');

    welcome_txt.classList.add('hide');
    user_email.classList.add('hide');
    user_status.classList.add('hide');
    reset_password_txt.classList.add('hide');
    verify_researcher_btn.classList.add('hide');
    btnLogout.classList.add('hide');
    recent_observations.classList.add('hide');
    no_observations.classList.add('hide');

    have_account_txt.classList.add('hide');
    txtEmail_login.classList.add('hide');
    txtPassword_login.classList.add('hide');
    btnLogin.classList.add('hide');
    forgot_password_btn.classList.add('hide');

    dont_have_account_txt.classList.add('hide');
    txtUsername_create.classList.add('hide');
    txtEmail_create.classList.add('hide');
    txtPassword_create.classList.add('hide');
    txtPassword_create_2.classList.add('hide');
    btnSignUp.classList.add('hide');

  });

  // Add a realtime listener
  firebase.auth().onAuthStateChanged(firebaseUser => {
	if(firebaseUser){

    if (firebaseUser.emailVerified) {
      //console.log('Email is verified');

      var user_name_query = firebase.database().ref("speciesid/accounts/").child(firebaseUser.uid);

      user_name_query.once('value').then(function(snapshot) {
        welcome_txt.textContent = "Welcome " + snapshot.val().username + "!";
        user_email.textContent = "Email: " + firebaseUser.email;

        // TODO: Check marks to show verification progress

        if (snapshot.val().researcher <= 0) {
          user_status.textContent = "Account: User (Get 3 Verification Checks to be a Researcher!)";
          check_mark.classList.add('hide');
          check_mark2.classList.add('hide');
        }
        if (snapshot.val().researcher == 1) {
          user_status.textContent = "Account: User (Get 2 more Verification Checks to be a Researcher!)";
          check_mark.classList.remove('hide');
          check_mark2.classList.add('hide');
        }
        if (snapshot.val().researcher == 2) {
          user_status.textContent = "Account: User (Get 1 Verification Check to be a Researcher!)";
          check_mark.classList.remove('hide');
          check_mark2.classList.remove('hide');
        }
        if (snapshot.val().researcher >= 3){
          user_status.textContent = "Account: Researcher";
          check_mark.classList.add('hide');
          check_mark2.classList.add('hide');
        }

      });

      //console.log(firebaseUser);

      welcome_txt.classList.remove('hide');
      user_email.classList.remove('hide');
      user_status.classList.remove('hide');
      reset_password_txt.classList.remove('hide');
      verify_researcher_btn.classList.remove('hide');
      btnLogout.classList.remove('hide');
      recent_observations.classList.remove('hide');
      no_observations.classList.remove('hide');

      have_account_txt.classList.add('hide');
      txtEmail_login.classList.add('hide');
      txtPassword_login.classList.add('hide');
      btnLogin.classList.add('hide');
      forgot_password_btn.classList.add('hide');

      dont_have_account_txt.classList.add('hide');
      txtUsername_create.classList.add('hide');
      txtEmail_create.classList.add('hide');
      txtPassword_create.classList.add('hide');
      txtPassword_create_2.classList.add('hide');
      btnSignUp.classList.add('hide');

    } else {
      // Send email verification
      firebaseUser.sendEmailVerification(); 

      alert("Please check your email for verification");

    }

	} else {

	  //console.log('not logged in');

    welcome_txt.classList.add('hide');
    user_email.classList.add('hide');
    user_status.classList.add('hide');
    reset_password_txt.classList.add('hide');
    verify_researcher_btn.classList.add('hide');
	  btnLogout.classList.add('hide');
    recent_observations.classList.add('hide');
    no_observations.classList.add('hide');

    check_mark.classList.add('hide');
    check_mark2.classList.add('hide');

    have_account_txt.classList.remove('hide');
    txtEmail_login.classList.remove('hide');
    txtPassword_login.classList.remove('hide');
    btnLogin.classList.remove('hide');
    forgot_password_btn.classList.remove('hide');

    dont_have_account_txt.classList.remove('hide');
    txtUsername_create.classList.remove('hide');
    txtEmail_create.classList.remove('hide');
    txtPassword_create.classList.remove('hide');
    txtPassword_create_2.classList.remove('hide');
    btnSignUp.classList.remove('hide');
	  }
  });

}());
