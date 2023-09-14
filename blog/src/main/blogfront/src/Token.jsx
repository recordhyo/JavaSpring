import axios from "axios";
import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";

function Token(props) {
    const navigate = new useNavigate();
    const code = new URL(window.location.href).searchParams.get("code")
    const grantType = "authorization_code";
    const REST_API_KEY = process.env.REACT_APP_KAKAO_RESTAPIKEY
    const REDIRECT_URI = process.env.REACT_APP_KAKAO_REDIRECTURL
    const SECRET_KEY = process.env.REACT_APP_KAKAO_SECRETKEY
    let POSTURL = `https://kauth.kakao.com/oauth/token?grant_type=${grantType}&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&code=${code}&client_secret=${SECRET_KEY}`

    if (code) {
        console.log(code)
        //localStorage.setItem("code", code)
    }


    useEffect(() => {
        const kakaoLogin = async () => {
            await axios({
                method: "GET",
                url: `/api/oauth2/kakao?code=${code}`,
                headers: {
                    "Content-Type": "application/json;charset=utf-8", //json형태로 데이터를 보내겠다는뜻
                    "Access-Control-Allow-Origin": "*", //이건 cors 에러때문에 넣어둔것. 당신의 프로젝트에 맞게 지워도됨
                },
            }).then((res) => { //백에서 완료후 우리사이트 전용 토큰 넘겨주는게 성공했다면
                //console.log(res);
                navigate("/");
            });
        };
        kakaoLogin();
    }, []);


    return (
        <div>
        </div>
    )


    //
    //
    // useEffect(() => {
    //     axios.get("/login/oauth2/code/kakao")
    //         .then((res) => {
    //             //setCode(res)
    //             console.log(res)
    //             console.log(res.data)
    //             //window.localStorage.setItem("code", code)
    //         })
    //         .catch()
    // }, [code]);


}
export default Token;