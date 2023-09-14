import React, {useEffect, useState} from "react";
import ApiService from "../../api/ApiService";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import kakao_btn from "../../kakao_btn.png"
import naver_btn from "../../naver_btn.png"
import google_btn from "../../google_btn.png"
import 'bootstrap/dist/css/bootstrap.css';

function Login() {
    const navigate = new useNavigate();
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    useEffect(() => {
        ApiService.loginpage()
            .then()
            .catch()
    }, []);

    const onEmailHandler = (e) => {
        setEmail(e.currentTarget.value);
    }

    const onPasswordHandler = (e) => {
        setPassword(e.currentTarget.value);
    }

    const goToSignupForm = () => {
        navigate('/usersignup')
    }

    const setcurrentuser = () => {

    }

    const submitHandler = (e) => {
        e.preventDefault();

        let body = {
            username: email,
            password: password,
        };
        axios.post("/login", body, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }})
            .then((res) => {
                 if(Array.isArray(res.data) === true)
                 {
                     //alert("로그인 성공")
                     axios.get("/currentuser")
                         .then((res) => {
                             console.log(res)
                             window.localStorage.setItem('id', res.data.email)
                         })

                     window.location.href='/'
                 }
                  else {
                      alert("로그인 실패")
                      console.log(res)
                      navigate('/')}
                }

    )};


    return (
        <div style={{
            display: 'flex', justifyContent: 'center', alignItems: 'center',

        }}>
            <form id="loginform" onSubmit={submitHandler} style={{ display: 'flex', flexDirection: 'column'}}>


                <h2>로그인</h2>
                <label>Email</label>
                <input type='email' value={email} name="username" onChange={onEmailHandler}/>
                <label>Password</label>
                <input type='password' value={password} name="password" onChange={onPasswordHandler}/>
                <br />
                <button type="submit" className="btn btn-outline-secondary mb-2" >로그인</button>
                <button className="btn btn-secondary mb-3" onClick={goToSignupForm}>회원가입</button>
                <p className="text-center">또는</p>
                <a href="https://kauth.kakao.com/oauth/authorize
?client_id=df19c3b3274e66a3b770a6f8d12b071c
&redirect_uri=http://192.168.0.35:3000/login/oauth2/code/kakao
&response_type=code">
                {/*<a href="http://192.168.0.35:8080/oauth2/authorization/kakao?redirect_uri=http://192.168.0.35:3000/login/oauth2/code/kakao">*/}
                    <img alt="kakao" className="text-center mb-2" src={kakao_btn} width="200px"/></a>
                <a href="http://192.168.0.35:8080/oauth2/authorization/naver">
                    <img alt="naver" className="text-center mb-2" src={naver_btn} width="200px"/></a>
                <a href="http://192.168.0.35:8080/oauth2/authorization/google">
                    <img alt="google" className="text-center mb-2" src={google_btn} width="200px"/></a>

            </form>
        </div>


    )
}

export default Login;