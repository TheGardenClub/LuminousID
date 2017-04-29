//
//  MyObservationsViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/27/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class MyObservationsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    var comments:[String] = ["comment1", "comment2"]
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

    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("comment: " + comment)
        print("datetime: " + datetime)
        print(gps_lat)
        print(gps_long)
        print(is_synced)
        print(is_verified)
        print("plant_code: " + plant_code)
        print("species_name: " + species_name)
        print("username:" + username)
        print("Photo Name: " + photoName)
        if species_name != ""{
            species_names = defaults.stringArray(forKey: "savedSpeciesNames") ?? [String]()
            datetimes = defaults.stringArray(forKey: "savedDateTimes") ?? [String]()
            synceds = defaults.array(forKey: "savedSynceds") as? [Bool] ?? [Bool]()
            photoNames = defaults.stringArray(forKey: "savedPhotoNames") ?? [String]()
            species_names.append(species_name)
            datetimes.append(datetime)
            synceds.append(is_synced)
            photoNames.append(fullPhotoName)
            defaults.set(species_names, forKey: "savedSpeciesNames")
            defaults.set(datetimes, forKey: "savedDateTimes")
            defaults.set(synceds, forKey: "savedSynceds")
            defaults.set(photoNames, forKey: "savedPhotoNames")
        }
        else{
            species_names = defaults.stringArray(forKey: "savedSpeciesNames") ?? [String]()
            datetimes = defaults.stringArray(forKey: "savedDateTimes") ?? [String]()
            synceds = defaults.array(forKey: "savedSynceds") as? [Bool] ?? [Bool]()
            photoNames = defaults.stringArray(forKey: "savedPhotoNames") ?? [String]()
        }
        // Do any additional setup after loading the view.
    }
    @IBAction func BackToMenu(_ sender: UIBarButtonItem) {
        navigationController?.popToRootViewController(animated: true)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return species_names.count
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "myObsCell", for: indexPath) as! MyObservationsTableViewCell
        if indexPath.row < (species_names.count-1){
        
        var fileUrl = try! FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: false).appendingPathComponent(photoNames[indexPath.row])

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
