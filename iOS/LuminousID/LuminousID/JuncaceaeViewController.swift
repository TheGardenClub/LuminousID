//
//  JuncaceaeViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 2/27/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit
import Firebase
import FirebaseDatabase
import FirebaseAuth


class JuncaceaeViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, RushesFilterTableProtocol{

    var myDict = [[String:AnyObject]]()
    var speciesNames:[String] = []
    var handle:FIRDatabaseHandle?
    var ref:FIRDatabaseReference?
    var row = 0
    var pressedFilters = false
    var originalDict = [[String:AnyObject]]()
    var originalSpeciesNames:[String] = []
    var listOfAttributes:[String] = []
    var listOfValues:[String] = []
    
    @IBOutlet weak var juncaceaeTable: UITableView!

    override func viewDidLoad() {
        super.viewDidLoad()
        ref = FIRDatabase.database().reference().child("speciesid").child("field_guide").child("graminoids").child("juncaceae")
        ref?.observe(.value, with: { (snapshot) in
            
            if let snapshots = snapshot.children.allObjects as? [FIRDataSnapshot]
            {
                self.myDict = snapshots.flatMap { $0.value as? [String:AnyObject]}
                for item in self.myDict{
                    self.speciesNames.append(item["species_name"] as! String)
                }
                
            }
            self.originalDict = self.myDict
            self.originalSpeciesNames = self.speciesNames
            self.juncaceaeTable.reloadData()
        })
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return speciesNames.count
    }
    
    @IBAction func FilterButton(_ sender: Any) {
        pressedFilters = true
        performSegue(withIdentifier: "toRushesFilters", sender: myDict)
    }
    
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "juncaceaeCell", for: indexPath) as! FieldGuideTableViewCell
        if indexPath.row <= (myDict.count - 1){
            cell.speciesNameCellLabel.text = self.speciesNames[indexPath.row]
            cell.commonNameCellLabel.text = myDict[indexPath.row]["common_name"] as? String
            let plantCodeString = myDict[indexPath.row]["plant_code"] as! String?
            cell.speciesPhoto.image = UIImage(named: "Images/" + plantCodeString! + "_1.jpg")
        }
        else{
            print("Table Reloaded.")
            juncaceaeTable.reloadData()
            
        }
        return (cell)
    }
    
    func filtersWereSelected(filterList: FilterList){
        var filterDict = [[String:AnyObject]]()
        var filteredSpeciesNames:[String] = []
        myDict = originalDict
        speciesNames = originalSpeciesNames
        var satisfiesFilter = true
        listOfAttributes = filterList.attributes
        listOfValues = filterList.values
        print (listOfAttributes)
        print (listOfValues)
        var att = listOfAttributes[0]
        var val = listOfValues[0]
        for item in myDict{
            for var i in 0...(listOfAttributes.count - 1){
                att = listOfAttributes[i]
                val = listOfValues[i]
                if (item[att] as? String)?.lowercased().range(of: val) != nil {
                    satisfiesFilter = true
                }
                else if val == "All"{
                    satisfiesFilter = true
                }
                else{
                    satisfiesFilter = false
                    break
                }
            }
            if satisfiesFilter == true{
                filterDict.append(item)
                filteredSpeciesNames.append(item["species_name"] as! String)
            }
        }
        myDict = filterDict
        speciesNames = filteredSpeciesNames
        juncaceaeTable.reloadData()
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        pressedFilters = false
        row = indexPath.row
        tableView.deselectRow(at: indexPath, animated: true)
        performSegue(withIdentifier: "toSpeciesFromJuncaceae", sender: speciesNames[indexPath.row])
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if pressedFilters == false{
            let speciesInfoVC = segue.destination as! SpeciesInfoViewController
            speciesInfoVC.speciesDict = [myDict[row]]
        }
        else{
            let filtersVC = segue.destination as! RushesFiltersViewController
            filtersVC.delegate = self
            filtersVC.filterDict = myDict
        }
        
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
