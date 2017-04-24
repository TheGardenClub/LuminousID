//
//  ForbsSpecificFiltersViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/22/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

protocol ForbsSpecificFilterProtocol{
    func filterWasSelected(filter: FilterElement)
}

class ForbsSpecificFiltersViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    var attributeName = ""
    var attributeFilters = [String]()
    var row = 0
    
    @IBOutlet weak var navBar: UINavigationItem!
    
    
    var delegate:ForbsSpecificFilterProtocol?
    
    override func viewDidLoad() {
        
        self.navBar.title = attributeName

        if attributeName == "Family"{
            attributeFilters = ["All", "alliaceae", "apiaceae", "asteraceae", "boraginaceae", "brassicaceae", "campanulaceae", "caryophyllaceae", "crassulaceae", "fabaceae", "gentianaceae", "hydrophyllaceae", "liliaceae", "melanthiaceae", "montiaceae", "onagraceae", "orobanchaceae", "plantaginaceae", "polemoniaceae", "polygonaceae", "primulaceae", "ranunculaceae", "roseaceae", "saxifragaceae", "violaceae", "other"]
        }
        else if attributeName == "Petal Number"{
            attributeFilters = ["All", "3", "4", "5", "6", "6+", "absent"]
        }
        else if attributeName == "Habitat"{
            attributeFilters = ["All", "fellfield", "dry meadow", "moist meadow", "wet meadow", "snowbed", "talus", "subalpine"]
        }
        else if attributeName == "Flower Color"{
            attributeFilters = ["All", "blue", "green", "orange", "pink", "purple", "red", "white", "yellow", "other"]
        }
        else if attributeName == "Flower Shape"{
            attributeFilters = ["All", "campanulate", "composite", "funnelform", "labiate", "papilionaceous", "radial", "reflexed", "reflexed", "urceolate", "other"]
        }
        else if attributeName == "Leaf Shape"{
            attributeFilters = ["All", "oblong", "palmate", "round", "ternate", "other"]
        }
        else if attributeName == "Leaf Arrangement"{
            attributeFilters = ["All", "alternate", "basal", "cushion", "opposite", "whorled", "other"]
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
        let cell = tableView.dequeueReusableCell(withIdentifier: "forbsSpecificFilterCell", for: indexPath) as! ForbsSpecificCellTableViewCell
        cell.AttributeLabel.text = self.attributeFilters[indexPath.row]
        cell.AttributeImage.image = UIImage(named: "forbs_glossary_small_keyed/" + self.attributeFilters[indexPath.row] + ".png")
        if attributeName == "Flower Color"{
            if self.attributeFilters[indexPath.row] == "white" {
                cell.AttributeImage.backgroundColor = UIColor.white
            }
            else if self.attributeFilters[indexPath.row] == "blue"{
                cell.AttributeImage.backgroundColor = UIColor.blue
            }
            else if self.attributeFilters[indexPath.row] == "green"{
                cell.AttributeImage.backgroundColor = UIColor.green
            }
            else if self.attributeFilters[indexPath.row] == "orange"{
                cell.AttributeImage.backgroundColor = UIColor.orange
            }
            else if self.attributeFilters[indexPath.row] == "pink"{
                cell.AttributeImage.backgroundColor = UIColor(red: 1, green: 0.749, blue: 0.9059, alpha: 1.0)
            }
            else if self.attributeFilters[indexPath.row] == "purple"{
                cell.AttributeImage.backgroundColor = UIColor.purple
            }
            else if self.attributeFilters[indexPath.row] == "red"{
                cell.AttributeImage.backgroundColor = UIColor.red
            }
            else if self.attributeFilters[indexPath.row] == "yellow"{
                cell.AttributeImage.backgroundColor = UIColor.yellow
            }
        }
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
