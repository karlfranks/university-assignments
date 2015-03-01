/* 
 * File:   ceteceans.h
 * Author: karlfranks
 *
 * Created on 11 December 2014, 16:26
 */

#ifndef CETECEANS_H
#define	CETECEANS_H

#ifdef	__cplusplus
extern "C" {
#endif

struct new_observer{
    double latitude;
    double longitude;
    char user[5];
    }observer_list[100];
    
struct new_sighting{
    char user[5];
    char type;
    double bearing;
    double distance;
}sighting_list[100];

struct output{
        double latitude;
        double longitude;
        char type;
        char user[5];
    }outputs[100];


#ifdef	__cplusplus
}
#endif

#endif	/* CETECEANS_H */

