//
//  Observation.swift
//  LuminousID
//
//  Created by Brian Larson on 4/27/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit

class Observation: NSObject {
    var speciesName: String
    var timestamp: Int
    var date: Date
    var image: String
    var uid: String
    var lat: Double
    var long: Double
    
    init(speciesName: String, timestamp: Int, date: Date, image: String, uid: String, lat: Double, long: Double){
        self.speciesName = speciesName
        self.timestamp = timestamp
        self.date = date
        self.image = image
        self.uid = uid
        self.lat = lat
        self.long = long
    }
    
}
