import React from "react";
import {Navigate, Outlet} from "react-router-dom";

function PrivateRouter() {
    const currentUser = window.localStorage.getItem("id");
    const currentOauth = window.localStorage.getItem("token")

    if (currentUser || currentOauth){
        return <Outlet/>
    }
    else {
        alert("로그인을 하세요")
        return <Navigate to="/"/>;
    }
}

export default PrivateRouter;