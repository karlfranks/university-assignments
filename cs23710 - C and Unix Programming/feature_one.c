#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ceteceans.h"
#include "navigation.h"
#include <math.h>
#include <unistd.h>

int total_observers;
int total_sightings;

void feature_one(){
    //Variables
    char observer_file[256];
    char sightings_file[256];
    printf("Enter the location of the observer file: ");
    scanf(" %s", observer_file);
    printf("Enter the location of the sightings file: ");
    scanf(" %s", sightings_file);
    
    //Declarations of functions
    int get_num_observers(char observer[256]);
    int get_num_sightings(char sighting[256]);
    void read_observer(char observer[256]);
    void read_sighting(char sighting[256]);
    void output_table();
    
    //Store amount of observers and sightings
    total_observers =  get_num_observers(observer_file);
    total_sightings =  get_num_sightings(sightings_file);
    
    //Read in the data
    read_observer(observer_file);
    read_sighting(sightings_file);
    
    //Calculate and output the table
    output_table();
}

//Opens file and counts number of newline characters (and decrements final value)
int get_num_observers(char observer[256]){
    
    FILE * observers;
    observers = fopen(observer,"r");
    if (observers == NULL){
        printf("Issue opening observers file!\n");
        exit(1);
    }
    
    int num_observers = 0;
    
    //Adapted Code
    int ch;
    do 
    {
        ch = fgetc(observers);
        if(ch == '\n') num_observers++;
    } while (ch != EOF);
    //End Adapted Code
    num_observers = num_observers - 1;
    fclose(observers);
    return num_observers;
}

//Opens file and counts number of newline characters
int get_num_sightings(char sighting[256]){
    
    FILE * sightings;
    sightings = fopen(sighting,"r");
    if (sightings == NULL){
        printf("Issue opening sightings file!\n");
        exit(1);
    }
    
    int num_sightings = 0;
    //Adapted Code
    int ch;
    do 
    {
        ch = fgetc(sightings);
        if(ch == '\n') num_sightings++;
    } while (ch != EOF);
    //End Adapted Code
    fclose(sightings);
    return num_sightings;
}

void read_observer(char observer[256]){
    //Temporary variables
    char date_and_time[20];
    double latitude_in;
    double longitude_in;
    char user_in[5];
    
    //Open file
    FILE * observers;
    observers = fopen(observer,"r");
    if (observers == NULL){
        printf("Issue opening file!\n");
        exit(1);
    }
    
    //Gets the date and time, which is always 20 characters long
    fgets(date_and_time, 20, observers);
    
    //Loop through other lines, reading in and storing data
    int loop = 0;
    while(loop<total_observers){
        
        fscanf(observers, "%s %lf %lf", user_in, &latitude_in, &longitude_in);

        strcpy(observer_list[loop].user, user_in);
        observer_list[loop].latitude = latitude_in;
        observer_list[loop].longitude = longitude_in;
        
        loop++;
    }
    
    fclose(observers);
}

void read_sighting(char sighting[256]){
    //Temporary variables
    char user_in[5];
    char type_in;
    double bearing_in;
    double distance_in;
    
    //Open file
    FILE * sightings;
    sightings = fopen(sighting,"r");
    if (sightings == NULL){
        printf("Issue opening file!\n");
        exit(1);
    }
    
    //Loop through the amount of times needed to read in the data, and store it
    int loop = 0;
    while(loop<total_sightings){
        
        fscanf(sightings, "%s %c %lf %lf", user_in, &type_in, &bearing_in, &distance_in);

        strcpy(sighting_list[loop].user, user_in);
        sighting_list[loop].type = type_in;
        sighting_list[loop].bearing = bearing_in;
        sighting_list[loop].distance = distance_in;
        
        loop++;
    }
    
    fclose(sightings);
}

void output_table(){
    double olat, olatr, olong, bg, bgr, range, cmlat, cmlong;
    char type_in;
    char user_in[5];
    int final_sightings = total_sightings;
    int i = 0;
    int j = 0;
    int k = 0;
    int observer_val;
    while(i<total_sightings){
        //Get data from sighting list
        type_in = sighting_list[i].type;
        bg = sighting_list[i].bearing;
        range = sighting_list[i].distance;
        strcpy(user_in, sighting_list[i].user);
        
        //Get user details from observer list
        while(k<total_observers){
            if(strcmp(user_in, observer_list[k].user) == 0){
                observer_val = k;
            }
            k++;
        }
        olat = observer_list[observer_val].latitude;
        olong = observer_list[observer_val].longitude;
        
        //Calculate output
        bgr = (bg * M_PI)/180;
        olatr = (olat * M_PI)/180;
        cmlat = olat + (range * cos(bgr)) / 60;
        cmlong = olong +(range * sin(bgr))/cos(olatr) / 60;

        //Only store data in output struct if within accepted area
        if(cmlat<52.833 && cmlat>52){
            if(cmlong<-4 && cmlong>-5){
                strcpy(outputs[i].user, user_in);
                outputs[i].latitude = cmlat;
                outputs[i].longitude = cmlong;
                outputs[i].type = type_in;
            }
        }
        else{
            final_sightings--;
        }
        
        i++;
    }
    
    //Print table of output
    printf("FEATURE ONE: LOCATION OF CETECEANS\n");
    printf("LATITUDE\tLONGITUDE\tTYPE\tSPOTTED BY\n");
    while(j<final_sightings){
        printf("%lf\t%lf\t%c\t%s\n", outputs[j].latitude, outputs[j].longitude, outputs[j].type, outputs[j].user);
        j++;
    }
}