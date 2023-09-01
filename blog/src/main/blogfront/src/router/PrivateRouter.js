import React from "react";
import {Navigate, Outlet} from "react-router-dom";

function PrivateRouter() {
    const currentUser = window.sessionStorage.getItem("id");

    if (currentUser){
        return <Outlet/>
    }
    else {
        alert("로그인을 하세요")
        return <Navigate to="/"/>;
    }
}

export default PrivateRouter;