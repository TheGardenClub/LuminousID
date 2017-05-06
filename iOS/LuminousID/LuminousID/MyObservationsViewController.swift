//
//  MyObservationsViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/27/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit
import Firebase
import FirebaseAuth
import FirebaseDatabase
import FirebaseStorage


/*
    This view is complicated, but it manages the data persistence of all of the observations. All photos are saved to the documents folder in the App directory. Everything else is saved in a list with user defaults. These values are retrieved and updated everytime this screen is launched. This view displays all of the user's observations.
 */

class MyObservationsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    var comments:[String] = []
    @IBOutlet weak var myObsTable: UITableView!
    var timestamps:[String] = []
    var dates:[String] = []
    var times:[String] = []
    var datetimes:[String] = []
    var synceds:[Bool] = []
    var species_names:[String] = []
    var lats:[Double] = []
    var longs:[Double] = []
    var verifieds:[Int] = []
    var plant_codes:[String] = []
    let defaults = UserDefaults.standard
    var usernames:[String] = []
    var photoNames:[String] = []
    var gpsAccuracys:[Double] = []
    var obsNames:[String] = []
    var fullPhotoNames:[String] = []
    var comment:String = ""
    var datetime:String = ""
    var date:String = ""
    var time:String = ""
    var gps_lat:Double = 0.0
    var gps_long:Double = 0.0
    var is_synced:Bool = false
    var is_verified:Int = 0
    var plant_code:String = ""
    var species_name:String = ""
    var username:String = ""
    var photoName: String = ""
    var fullPhotoName: String = ""
    var fName: String = ""
    var ref:FIRDatabaseReference?
    var userNamePath = ""
    var gpsAccuracy = 0.0
    var user = FIRAuth.auth()?.currentUser
    let storage = FIRStorage.storage()
    var row = 0
    
    /*
        Retrieve all of the stored data from user defaults
     */
    
    override func viewDidLoad() {
        ref = FIRDatabase.database().reference()
        super.viewDidLoad()
 

        if species_name != ""{
            species_names = defaults.stringArray(forKey: "savedSpeciesNames") ?? [String]()
            datetimes = defaults.stringArray(forKey: "savedDateTimes") ?? [String]()
            synceds = defaults.array(forKey: "savedSynceds") as? [Bool] ?? [Bool]()
            photoNames = defaults.stringArray(forKey: "savedPhotoNames") ?? [String]()
            comments = defaults.stringArray(forKey: "savedComments") ?? [String]()
            lats = defaults.array(forKey: "savedLats") as? [Double] ?? [Double]()
            longs = defaults.array(forKey: "savedLongs") as? [Double] ?? [Double]()
            verifieds = defaults.array(forKey: "savedVerifieds") as? [Int] ?? [Int]()
            plant_codes = defaults.stringArray(forKey: "savedPlantCodes") ?? [String]()
            usernames = defaults.stringArray(forKey: "savedUsernames") ?? [String]()
            gpsAccuracys = defaults.array(forKey: "savedAccuracys") as? [Double] ?? [Double]()
            fullPhotoNames = defaults.stringArray(forKey: "savedFullPhotoNames") ?? [String]()
            
            species_names.append(species_name)
            datetimes.append(datetime)
            synceds.append(is_synced)
            photoNames.append(photoName)
            comments.append(comment)
            lats.append(gps_lat)
            longs.append(gps_long)
            verifieds.append(is_verified)
            plant_codes.append(plant_code)
            usernames.append(username)
            gpsAccuracys.append(gpsAccuracy)
            fullPhotoNames.append(fullPhotoName)
            
            
            defaults.set(species_names, forKey: "savedSpeciesNames")
            defaults.set(datetimes, forKey: "savedDateTimes")
            defaults.set(synceds, forKey: "savedSynceds")
            defaults.set(photoNames, forKey: "savedPhotoNames")
            defaults.set(comments, forKey: "savedComments")
            defaults.set(lats, forKey: "savedLats")
            defaults.set(longs, forKey: "savedLongs")
            defaults.set(verifieds, forKey: "savedVerifieds")
            defaults.set(plant_codes, forKey: "savedPlantCodes")
            defaults.set(usernames, forKey: "savedUsernames")
            defaults.set(gpsAccuracys, forKey: "savedAccuracys")
            defaults.set(fullPhotoNames, forKey: "savedFullPhotoNames")
            
        }
        else{
            species_names = defaults.stringArray(forKey: "savedSpeciesNames") ?? [String]()
            datetimes = defaults.stringArray(forKey: "savedDateTimes") ?? [String]()
            synceds = defaults.array(forKey: "savedSynceds") as? [Bool] ?? [Bool]()
            photoNames = defaults.stringArray(forKey: "savedPhotoNames") ?? [String]()
            comments = defaults.stringArray(forKey: "savedComments") ?? [String]()
            lats = defaults.array(forKey: "savedLats") as? [Double] ?? [Double]()
            longs = defaults.array(forKey: "savedLongs") as? [Double] ?? [Double]()
            verifieds = defaults.array(forKey: "savedVerifieds") as? [Int] ?? [Int]()
            plant_codes = defaults.stringArray(forKey: "savedPlantCodes") ?? [String]()
            usernames = defaults.stringArray(forKey: "savedUsernames") ?? [String]()
            gpsAccuracys = defaults.array(forKey: "savedAccuracys") as? [Double] ?? [Double]()
            fullPhotoNames = defaults.stringArray(forKey: "savedFullPhotoNames") ?? [String]()
            
        }
        // Do any additional setup after loading the view.
    }
    
    /*
        Custom back button that pops the VC
     */
    
    @IBAction func BackToMenu(_ sender: UIBarButtonItem) {
        navigationController?.popToRootViewController(animated: true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    /*
        make a row for every observation
     */
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return species_names.count
    }
    
    /*
        Specifies what goes into each cell. In this case, Species Name, Date, is_synced, and Image
     */
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "myObsCell", for: indexPath) as! MyObservationsTableViewCell
        if indexPath.row <= (species_names.count-1){
        
        var fileUrl = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent(fullPhotoNames[indexPath.row])

        cell.myObsSpeciesLabel.text = species_names[indexPath.row]
        cell.myObsDateLabel.text = datetimes[indexPath.row]
        cell.myObsImageView.image = UIImage(contentsOfFile: fileUrl.path)!
        if synceds[indexPath.row] == false{
            cell.myObsSyncedLabel.text = "Not Synced"
            cell.myObsSyncedLabel.textColor = UIColor.red
        }
        else{
            cell.myObsSyncedLabel.text = "Synced"
            cell.myObsSyncedLabel.textColor = UIColor.green
        }
        }
        else{
            myObsTable.reloadData()
        }
        
        return (cell)
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        row = indexPath.row

        tableView.deselectRow(at: indexPath, animated: true)
        performSegue(withIdentifier: "toViewObservation", sender: species_names[indexPath.row])
    }
    
    /*
        Passes all the metadata of the observation over to the next VC
     */
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let viewObsVC = segue.destination as! ViewObservationViewController

        viewObsVC.obsDate = datetimes[row]

        viewObsVC.obsLat = lats[row]

        viewObsVC.obsLong = longs[row]

        viewObsVC.speciesImageName = fullPhotoNames[row]
        
        viewObsVC.speciesName = species_names[row]

        viewObsVC.obsNotes = comments[row]
    }
    
    /*
        Creates observations in Firebase and updates all of their information accordingly
     */
    
    @IBAction func TopSyncButton(_ sender: Any) {
        let storageRef = storage.reference()
        var obsRef = storage.reference()
        
        let metaData = FIRStorageMetadata()
        metaData.contentType = "image/jpg"
        
        for var i in 0...(species_names.count - 1){
        var fileUrl = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent(fullPhotoNames[i])
        var obsImage = try! Data(contentsOf: fileUrl)
        obsRef = storageRef.child("observations/"+fullPhotoNames[i])
        var uploadTask = obsRef.put(obsImage, metadata: metaData) { (metaData, error) in
            if var error = error {
                print(error.localizedDescription)
                return
            }
            else{
            // Metadata contains file metadata such as size, content-type, and download URL.
            var downloadURL = metaData?.downloadURL
            }
        }
        self.ref?.child("speciesid").child("observations").child(photoNames[i]).setValue(["species_name": species_names[i]])
        self.ref?.child(("speciesid/observations/" + photoNames[i] + "/comments")).setValue(comments[i])
        self.ref?.child(("speciesid/observations/" + photoNames[i] + "/gps_accuracy")).setValue(gpsAccuracys[i])
        self.ref?.child(("speciesid/observations/" + photoNames[i] + "/gps_lat")).setValue(lats[i])
        self.ref?.child(("speciesid/observations/" + photoNames[i] + "/gps_long")).setValue(longs[i])
        self.ref?.child(("speciesid/observations/" + photoNames[i] + "/is_synced")).setValue(true)
        self.ref?.child(("speciesid/observations/" + photoNames[i] + "/is_verified")).setValue(verifieds[i])
        self.ref?.child(("speciesid/observations/" + photoNames[i] + "/plant_code")).setValue(plant_codes[i])
        self.ref?.child(("speciesid/observations/" + photoNames[i] + "/datetime")).setValue(datetimes[i])
        self.ref?.child(("speciesid/observations/" + photoNames[i] + "/username")).setValue(usernames[i])
        synceds[i] = true
        }
        defaults.set(synceds, forKey: "savedSynceds")
        myObsTable.reloadData()
    }
    
    /*
        Same as above, but this button was removed
     */
    
    @IBAction func BottomSyncButton(_ sender: Any) {
        let storageRef = storage.reference()
        var obsRef = storage.reference()
        
        let metaData = FIRStorageMetadata()
        metaData.contentType = "image/jpg"
        
        for var i in 0...(species_names.count - 1){
            var fileUrl = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent(fullPhotoNames[i])
            var obsImage = try! Data(contentsOf: fileUrl)
            obsRef = storageRef.child("observations/"+fullPhotoNames[i])
            var uploadTask = obsRef.put(obsImage, metadata: metaData) { (metaData, error) in
                if var error = error {
                    print(error.localizedDescription)
                    return
                }
                else{
                    // Metadata contains file metadata such as size, content-type, and download URL.
                    var downloadURL = metaData?.downloadURL
                }
            }
            self.ref?.child("speciesid").child("observations").child(photoNames[i]).setValue(["species_name": species_names[i]])
            self.ref?.child(("speciesid/observations/" + photoNames[i] + "/comments")).setValue(comments[i])
            self.ref?.child(("speciesid/observations/" + photoNames[i] + "/gps_accuracy")).setValue(gpsAccuracys[i])
            self.ref?.child(("speciesid/observations/" + photoNames[i] + "/gps_lat")).setValue(lats[i])
            self.ref?.child(("speciesid/observations/" + photoNames[i] + "/gps_long")).setValue(longs[i])
            self.ref?.child(("speciesid/observations/" + photoNames[i] + "/is_synced")).setValue(true)
            self.ref?.child(("speciesid/observations/" + photoNames[i] + "/is_verified")).setValue(verifieds[i])
            self.ref?.child(("speciesid/observations/" + photoNames[i] + "/plant_code")).setValue(plant_codes[i])
            self.ref?.child(("speciesid/observations/" + photoNames[i] + "/datetime")).setValue(datetimes[i])
            self.ref?.child(("speciesid/observations/" + photoNames[i] + "/username")).setValue(usernames[i])
            synceds[i] = true
        }
        defaults.set(synceds, forKey: "savedSynceds")
        myObsTable.reloadData()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        myObsTable.reloadData()
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
