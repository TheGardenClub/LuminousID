//
//  FilterElement.swift
//  LuminousID
//
//  Created by Brian Larson on 4/22/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class FilterElement: NSObject {
    var attribute: String
    var value: String
    
    init(attribute: String, value: String){
        self.attribute = attribute
        self.value = value
    }

}
