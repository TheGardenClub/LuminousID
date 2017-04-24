//
//  SedgesSpecificFiltersViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/24/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

protocol RushesSpecificFilterProtocol{
    func filterWasSelected(filter: FilterElement)
}

class RushesSpecificFiltersViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    var attributeName = ""
    var attributeFilters = [String]()
    var row = 0
    
    
    @IBOutlet weak var navBar: UINavigationItem!
    
    
    var delegate:RushesSpecificFilterProtocol?
    
    override func viewDidLoad() {
        
        self.navBar.title = attributeName
        
        if attributeName == "Stem Cross-section"{
            attributeFilters = ["All", "round and hollow", "round and solid", "triangular and solid"]
        }
        else if attributeName == "Leaf Blade"{
            attributeFilters = ["All", "flat", "involute", "keeled", "absent"]
        }
        else if attributeName == "Inflorescence"{
            attributeFilters = ["All", "open", "contracted"]
        }
        else if attributeName == "Florets Per Spikelet"{
            attributeFilters = ["All", "1", "2+"]
        }
        else if attributeName == "Awns"{
            attributeFilters = ["All", "bent", "straight", "twisted", "absent"]
        }
        else if attributeName == "Habitat"{
            attributeFilters = ["All", "fellfield", "dry meadow", "moist meadow", "wet meadow", "snowbed", "talus", "subalpine"]
        }
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return attributeFilters.count
    }
    
    
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "rushesSpecificFilterCell", for: indexPath) as! ForbsSpecificCellTableViewCell
        cell.AttributeLabel.text = self.attributeFilters[indexPath.row]
        cell.AttributeImage.image = UIImage(named: "grams_glossary_small_keyed/" + self.attributeFilters[indexPath.row] + ".png")
        return (cell)
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        row = indexPath.row
        tableView.deselectRow(at: indexPath, animated: true)
        let filterChoice = FilterElement(attribute: attributeName, value: attributeFilters[indexPath.row])
        self.delegate?.filterWasSelected(filter: filterChoice)
        self.navigationController!.popViewController(animated: true)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
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
