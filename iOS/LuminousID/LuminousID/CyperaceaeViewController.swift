//
//  CyperaceaeViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 2/27/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit
import Firebase
import FirebaseDatabase
import FirebaseAuth


class CyperaceaeViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var cyperaceaeTable: UITableView!
    var myDict = [[String:AnyObject]]()
    var speciesNames:[String] = []
    var handle:FIRDatabaseHandle?
    var ref:FIRDatabaseReference?
    var row = 0
    var pressedFilters = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        ref = FIRDatabase.database().reference().child("speciesid").child("field_guide").child("graminoids").child("cyperaceae")
        ref?.observe(.value, with: { (snapshot) in
            
            if let snapshots = snapshot.children.allObjects as? [FIRDataSnapshot]
            {
                self.myDict = snapshots.flatMap { $0.value as? [String:AnyObject]}
                for item in self.myDict{
                    self.speciesNames.append(item["species_name"] as! String)
                }
                
            }
            self.cyperaceaeTable.reloadData()
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
    
    
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cyperaceaeCell", for: indexPath) as! FieldGuideTableViewCell
        
        if indexPath.row <= (myDict.count - 1){
            cell.speciesNameCellLabel.text = self.speciesNames[indexPath.row]
            cell.commonNameCellLabel.text = myDict[indexPath.row]["common_name"] as? String
            let plantCodeString = myDict[indexPath.row]["plant_code"] as! String?
            cell.speciesPhoto.image = UIImage(named: "Images/" + plantCodeString! + "_1.jpg")
        }
        else{
            print("Table Reloaded.")
            cyperaceaeTable.reloadData()
            
        }
        return (cell)
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        pressedFilters = false
        row = indexPath.row
        tableView.deselectRow(at: indexPath, animated: true)
        performSegue(withIdentifier: "toSpeciesFromCyperaceae", sender: speciesNames[indexPath.row])
    }
    
    @IBAction func FilterButton(_ sender: Any) {
        pressedFilters = true
        performSegue(withIdentifier: "toSedgesFilters", sender: myDict)
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if pressedFilters == false{
            let speciesInfoVC = segue.destination as! SpeciesInfoViewController
            speciesInfoVC.speciesDict = [myDict[row]]
        }
        else{
            let filtersVC = segue.destination as! SedgesFiltersViewController
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
