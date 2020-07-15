import axios from 'axios';
const base_url = 'http://104.197.101.74:8080';

export const API = {
    getStationList : async()=>{
        const url = base_url + '/';
        return await axios.get(
            url
        ).catch(e => console.log('Error: ', e));
    },
    getRouteListofStation : async(requestBody)=>{
        const url = base_url + '/routes/';
        return await axios.post(
            url,{...requestBody}
        ).catch(e => console.log(e));
    },
    getPairTime : async(requestBody)=>{
        const url = base_url +'/time/';
        return await axios.post(
            url,{...requestBody}
        ).catch(e => console.log(e));
    },
    addTimeEntry : async(requestBody)=>{
        const url = base_url + '/entry/';
        return await axios.post(
            url,{...requestBody}
        ).catch(e=>console.log(e));
    }
}