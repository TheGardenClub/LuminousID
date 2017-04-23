//
//  FilterList.swift
//  LuminousID
//
//  Created by Brian Larson on 4/22/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class FilterList: NSObject {
    var attributes: [String]
    var values: [String]
    
    init(attributes: [String], values: [String]){
        self.attributes = attributes
        self.values = values
    }
}
