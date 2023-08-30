import React, {useEffect, useState} from "react";
import ApiService from "./ApiService";
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

    const onClickLogin = () => {
        axios.post("http://localhost:8080/login", {
            username: email,
            password: password
        }).then((res) => {
            alert(res.data)
            // if(res.data.email === undefined){
            //     alert("입력하신 id가 존재하지 않습니다")
            // } else if (res.data.email === null){
            //     alert("입력하신 비밀번호가 일치하지 않습니다")
            // } else if (res.data.email === email){
            //     sessionStorage.setItem("email", email);
            //     sessionStorage.setItem("password", password);
            // }
            //document.location.href = "/";
        })
            .catch();
    }

    const backToArticleList = () => {
        navigate('/articles')
    }

    return (
        <div style={{
            display: 'flex', justifyContent: 'center', alignItems: 'center',
            width: '100%', height: '100vh'
        }}>
            <h2>로그인</h2>
            <form style={{ display: 'flex', flexDirection: 'column'}}>
                <label>Email</label>
                <input type='email' value={email} name="username" onChange={onEmailHandler}/>
                <label>Password</label>
                <input type='password' value={password} name="password" onChange={onPasswordHandler}/>
                <br />
                <button type="submit" onClick={() => {onClickLogin(); backToArticleList();}}>
                    Login
                </button>
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