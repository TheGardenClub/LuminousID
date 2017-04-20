//
//  MapsViewController.swift
//  LuminousID
//
//  Created by Kevin Rau on 4/19/17.
//  Copyright © 2017 Garden Club. All rights reserved.
//

import UIKit
import MapKit
import Firebase
import FirebaseDatabase
import FirebaseAuth
import GeoFire

class MapsViewController: UIViewController, MKMapViewDelegate, CLLocationManagerDelegate {
    
    @IBOutlet weak var mapView: MKMapView!
    
    let locationManager = CLLocationManager()
    var mapHasCenteredOnce = false
    var geoFire: GeoFire!
    var geoFireRef: FIRDatabaseReference!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        mapView.delegate = self
        mapView.userTrackingMode = MKUserTrackingMode.follow //The map follows user's location
        
        geoFireRef = FIRDatabase.database().reference()
        geoFire = GeoFire(firebaseRef: geoFireRef)
        
    }
    
    override func viewDidAppear(_ animated: Bool) {
        locationAuthStatus()
    }
    
    func locationAuthStatus() {
        //Only find location when app is in use
        if CLLocationManager.authorizationStatus() == .authorizedWhenInUse {
            
            mapView.showsUserLocation = true //Shows user location
        } else {
            
            locationManager.requestWhenInUseAuthorization() //Prompt "can I access your location"
        }
        
    }
    
    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        
        if status == CLAuthorizationStatus.authorizedWhenInUse {
            
            mapView.showsUserLocation = true
            
        }
        
    }
    
    //Determining the size of region shown in the app upon launch
    func centerMapOnLocation(location: CLLocation) {
        
        let coordinateRegion = MKCoordinateRegionMakeWithDistance(location.coordinate, 2000, 2000)
        
        mapView.setRegion(coordinateRegion, animated: true)
        
    }
    
    //Tells map to center
    func mapView(_ mapView: MKMapView, didUpdate userLocation: MKUserLocation) {
        
        if let loc = userLocation.location {
            
            //Makes sure the map doesn't keep recentering while someone is walking and trying to pan to find pokemon
            if !mapHasCenteredOnce {
                centerMapOnLocation(location: loc)
                mapHasCenteredOnce = true
            }
        }
    }
    


}
