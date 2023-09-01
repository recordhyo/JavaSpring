import React, {useEffect, useState} from "react";
import ApiService from "../../api/ApiService";
import axios from "axios";
import {useNavigate} from "react-router-dom";


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

    const submitHandler = (e) => {
        e.preventDefault();
        console.log(email);
        console.log(password);

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
                     alert("로그인 성공")
                     console.log(res)
                     axios.get("/currentuser")
                         .then((res) => {
                             console.log(res)
                             window.sessionStorage.setItem('id', res.data)
                         })

                     window.location.replace('/articles')
                 }
                  else {
                      alert("로그인 실패")
                      console.log(res)
                      navigate('/')}
                }

    )};


    const backToArticleList = () => {
        navigate('/articles')
    }

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
                <button type="submit" >
                    Login</button>
            </form>
        </div>


    )
}

export default Login;