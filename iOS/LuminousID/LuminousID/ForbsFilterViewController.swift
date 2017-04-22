//
//  ForbsFilterViewController.swift
//  LuminousID
//
//  Created by Brian Larson on 4/16/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class ForbsFilterViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource, UITextFieldDelegate {

    @IBOutlet weak var InflorescenceBox: UITextField!
    @IBOutlet weak var PetalNumberBox: UITextField!
    @IBOutlet weak var InflorescencePicker: UIPickerView!
    @IBOutlet weak var PetalNumberPicker: UIPickerView!
    
    var inflorescenceOptions = ["I1", "I2", "I3"]
    var petalNumberOptions = ["1", "2", "3"]
    
    
    var filterDict = [[String:AnyObject]]()
    override func viewDidLoad() {
        super.viewDidLoad()
        print (filterDict)
        // Do any additional setup after loading the view.
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        var countRows : Int = inflorescenceOptions.count
        
        if pickerView == InflorescencePicker{
            countRows = self.inflorescenceOptions.count
        }
        else if pickerView == PetalNumberPicker{
            countRows = self.petalNumberOptions.count
        }
        
        return countRows
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        
        if pickerView == InflorescencePicker {
            
            let titleRow = inflorescenceOptions[row]
            return titleRow
            
        }
        
        else if pickerView == PetalNumberPicker {
            
            let titleRow = petalNumberOptions[row]
            return titleRow
            
        }
        return ""
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if pickerView == InflorescencePicker {
            
            self.InflorescenceBox.text = self.inflorescenceOptions[row]
            self.InflorescencePicker.isHidden = true
            
        }
            
        else if pickerView == PetalNumberPicker {
            
            self.PetalNumberBox.text = self.petalNumberOptions[row]
            self.PetalNumberPicker.isHidden = true
            
        }
    }
    func textFieldDidBeginEditing(_ textField: UITextField) {
        if (textField == self.InflorescenceBox){
            self.InflorescencePicker.isHidden = false
        }
        else if (textField == self.PetalNumberBox){
            self.PetalNumberPicker.isHidden = false
        }
    }
    
    
        
        
        
        
        
        
        
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
