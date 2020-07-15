import React, { useState, useEffect, Fragment } from 'react';
import { API } from '../API';

import { makeStyles } from '@material-ui/core/styles';
import Select from '@material-ui/core/Select';
import Button from '@material-ui/core/Button';
import Icon from '@material-ui/core/Icon';
import TextField from '@material-ui/core/TextField';
import FormControl from '@material-ui/core/FormControl';
import FormHelperText from '@material-ui/core/FormHelperText';
import MenuItem from '@material-ui/core/MenuItem';
import { Typography, Input } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
    formControl: {
      margin: theme.spacing(1),
      minWidth: 120,
    },
    selectEmpty: {
      marginTop: theme.spacing(2),
    },
  }));

const MainView  = () => {
    const classes = useStyles();
    const [station,setStation] = useState("");
    const [route,setRoute] = useState("");
    const [stationList,setStationList] = useState();
    const [routeList,setRouteList] = useState();
    const [time,setTime] = useState();
    const [entryTime,setEntryTime] = useState();

    useEffect(()=>{
        const fetchStationList = async ()=>{
            let receivedData = await API.getStationList();
            if(receivedData){
                setStationList(receivedData.data);
            }
        }
        fetchStationList();      
    },[]);

    const getRouteList = async (id)=>{
        var data;
        data = {stationId : id};
        let receivedData = await API.getRouteListofStation(data);
        if(receivedData){
            setRouteList(receivedData.data);
        }
    }

    const getAvgTime = async (id)=>{
        var data;
        data = {stationId : station, routeId: id};
        let receivedData = await API.getPairTime(data);
        if(receivedData){
            setTime(receivedData.data);
        }
    }


    const handleStationChange = async event=>{
        event.preventDefault();        
        setStation(event.target.value);
        getRouteList(event.target.value);
        setRoute("");
    };

    const handleRouteChange = async event=>{
        event.preventDefault();        
        setRoute(event.target.value);
        getAvgTime(event.target.value);
    };

    const handleButton = async event =>{
        event.preventDefault();
        var data;
        data = {stationId:station, routeId:route,time:entryTime};
        await API.addTimeEntry(data);
        window.location.reload(false);
    }

    const handleTextFieldChange = async event=>{
        event.preventDefault();
        setEntryTime(event.target.value);
    }

    return(
        <Fragment>
        {
            stationList ?
            <Fragment>    
            <FormControl variant="filled" className={classes.formControl}>
            <Select
                labelId="station-select-label"
                id="station-select"
                value={station}
                onChange={handleStationChange}
                displayEmpty
            >
            <MenuItem value="" disabled>
                <em>Station</em>
            </MenuItem>
            {stationList.map( (station,index)=>(
                    <MenuItem key = {index} value={station}>
                        {station}
                    </MenuItem>
                )
            )}
            </Select>
            <FormHelperText>Select your current station</FormHelperText>
            </FormControl>                  
            </Fragment>
            :<h2>Loading...</h2>
        }
        {
            routeList ?
            <Fragment>    
            <FormControl variant="filled" className={classes.formControl}>
            <Select
                labelId="route-select-label"
                id="route-select"
                value={route}
                onChange={handleRouteChange}
                displayEmpty
            >
            <MenuItem value="" disabled>
                <em>Route</em>
            </MenuItem>
            {routeList.map( (r,index)=>(
                    <MenuItem key = {index} value={r}>
                        {r}
                    </MenuItem>
                )
            )}
            </Select>
            <FormHelperText>Select your current route</FormHelperText>
            </FormControl>                  
            </Fragment>
            :<h2>Select a station</h2>
        }
        {
            (station != "" && route != "") ?
            <Fragment>
                <Typography>Average wait time for this station is: {Math.trunc(time)} minutes and {Math.trunc((time-Math.trunc(time))*60)} seconds</Typography>
                <Typography>Been waiting for too long? Share with us your wait time!</Typography>
                <TextField label="Your time (in minutes!)"
                type="number" onChange={handleTextFieldChange}>Enter your wait time</TextField>
                <Button variant="contained" color ="primary" endIcon={<Icon>send</Icon>} 
                onClick={handleButton}>Report new time
                </Button>
            </Fragment>
            : <div/>
        }
        </Fragment>
    );
};
export default MainView;