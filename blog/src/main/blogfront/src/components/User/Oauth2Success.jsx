import React, {useEffect, useState} from "react";
import axios from "axios";
import {withMobileDialog} from "@material-ui/core";
import {useNavigate, useParams} from "react-router-dom";


function Oauth2Success() {
    const params = useParams();
    const navigate = new useNavigate();


    useEffect(() => {
        const oauthsuccess = () => {
            console.log(params.userid)
            window.localStorage.setItem("userid", params.userid);

        }
        navigate("/");
        oauthsuccess();
    }, []);

    return (

        <></>
    )
}

export default Oauth2Success;