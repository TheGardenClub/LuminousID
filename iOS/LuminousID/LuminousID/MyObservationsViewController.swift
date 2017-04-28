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
    var timestamps:[String] = []
    var dates:[String] = []
    var times:[String] = []
    var lats:[Double] = []
    var longs:[Double] = []
    var synceds:[Bool] = []
    var verifieds:[Int] = []
    var plant_codes:[String] = []
    var species_names:[String] = []
    var usernames:[String] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return 1
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "myObsCell", for: indexPath) as! MyObservationsTableViewCell
        return (cell)
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
