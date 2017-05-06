//
//  ForbsFilterTableViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/20/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

/*
    This view manages the filter categories for the Forbs Table View VC. It generates lists of attributes, and values that it passes back for the updated table.
 */

/*
    Protocol for passing the information back to the Forbs Table VC
 */

protocol ForbsFilterTableProtocol{
    func filtersWereSelected(filterList: FilterList)
}


class ForbsFilterTableViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, ForbsSpecificFilterProtocol {
    
    var filterDict = [[String:AnyObject]]()
    var row = 0
    var filtersList = ["Family", "Flower Color", "Petal Number", "Flower Shape", "Leaf Arrangement", "Leaf Shape", "Habitat"]
    var filterFormattedList = ["family_name", "flower_color", "petal_number", "flower_shape", "leaf_arrangement", "leaf_shape_filter", "habitat"]
    var selectionList = ["All", "All", "All", "All", "All", "All", "All"]
    var filterAttribute = ""
    var filterValue = ""
    
    
    var delegate:ForbsFilterTableProtocol?
    
    
    
    @IBOutlet weak var forbsFilterTable: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    /*
        On search button press, return the lists to the Forbs Table VC
     */
    
    @IBAction func SearchButton(_ sender: UIButton) {
        let filters = FilterList(attributes: filterFormattedList, values: selectionList)
        self.delegate?.filtersWereSelected(filterList: filters)
        self.navigationController!.popViewController(animated: true)
    }
    
    /*
        Make a row for every item in filtersList
     */
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return filtersList.count
    }
    
    /*
        Passes info to the Specific Filters VC so it knows which item was selected
     */
    
    func filterWasSelected(filter: FilterElement){
        filterAttribute = filter.attribute
        filterValue = filter.value
        print (filterAttribute)
        print (filterValue)
        if filterAttribute == "Family"{
            selectionList[0] = filterValue
        }
        else if filterAttribute == "Flower Color"{
            selectionList[1] = filterValue
        }
        else if filterAttribute == "Petal Number"{
            selectionList[2] = filterValue
        }
        else if filterAttribute == "Flower Shape"{
            selectionList[3] = filterValue
        }
        else if filterAttribute == "Leaf Arrangement"{
            selectionList[4] = filterValue
        }
        else if filterAttribute == "Leaf Shape"{
            selectionList[5] = filterValue
        }
        else if filterAttribute == "Habitat"{
            selectionList[6] = filterValue
        }
        forbsFilterTable.reloadData()
    }
    
    /*
        Manages the loyout of each cell, listing the filter category and the selection if there is one.
     */
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "forbsFilterCell", for: indexPath) as! FilterTableViewCell

        cell.FilterLabel.text = self.filtersList[indexPath.row]
        cell.SelectionLabel.text = self.selectionList[indexPath.row]

        return (cell)
    }
    
    /*
        Performs a segue to the Specific Filters VC when a selection is made
     */
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        row = indexPath.row
        tableView.deselectRow(at: indexPath, animated: true)
        performSegue(withIdentifier: "toForbsSpecificFilters", sender: filtersList[indexPath.row])
    }
    
    /*
        Prepares for the segue by passing the selected attribute to the Specific Filters VC
     */
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let specificFiltersVC = segue.destination as! ForbsSpecificFiltersViewController
        specificFiltersVC.delegate = self
        specificFiltersVC.attributeName = self.filtersList[row]
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
