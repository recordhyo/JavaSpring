import React, {useEffect, useState} from "react";
import ApiService from "./api/ApiService";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import qs from "qs";


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
                     localStorage.clear()
                     localStorage.setItem('id', res.data.id)
                     localStorage.setItem('token', res.data.token)
                     //window.location.replace('http://localhost:3000/articles')


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
            width: '100%', height: '100vh'
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

        // <div>
        //     <form>
        //         <label>Email</label>
        //         <input type='email' value={id} />
        //         <br/>
        //         <label>Password</label>
        //         <input type='password' value={pw}/>
        //         <br/>
        //         <button formAction=''>
        //             Login
        //         </button>
        //     </form>
        //
        // </div>

    )
}

export default Login;