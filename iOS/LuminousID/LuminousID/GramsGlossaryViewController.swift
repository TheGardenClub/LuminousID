//
//  GramsGlossaryViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/10/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit



class GramsGlossaryViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    var row = 0
    @IBOutlet weak var gramsGlossaryTable: UITableView!
    
    let gramsGlossaryList = ["Awn Absent", "Awn Bent", "Awn Straight", "Awn Twisted", "Awn", "Contracted", "Flat", "Floret", "Involute", "Keeled", "Open", "Spikelet", "Spikes Globose", "Spikes One", "Spikes Twoormore"]
    
    let gramsGlossaryImageList = ["awn_absent.png", "awn_bent.png", "awn_straight.png", "awn_twisted.png", "awn.png", "contracted.png", "flat.png", "floret.png", "involute.png", "keeled.png", "open.png", "spikelet.png", "spikes_globose.png", "spikes_one.png", "spikes_twoormore.png"]
    
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
        return (gramsGlossaryList.count)
    }
    
    
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "gramsGlossaryCell", for: indexPath) as! GlossaryTableViewCell
        if indexPath.row <= gramsGlossaryList.count{
            cell.GlossaryImageView.image = UIImage(named: "grams_glossary_small_keyed/" + gramsGlossaryImageList[indexPath.row])
        }
        else{
            print("End of Table Error Handled.")
        }
        cell.GlossaryLabel.text = gramsGlossaryList[indexPath.row]
        return (cell)
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        row = indexPath.row
        tableView.deselectRow(at: indexPath, animated: true)
        performSegue(withIdentifier: "toGlossaryInfoFromGrams", sender: gramsGlossaryList[indexPath.row])
    }
}
