import React, {useEffect, useState} from "react";
import ApiService from "./ApiService";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import qs from "qs";

function Login() {
    const navigate = new useNavigate();
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const axiosConfig = {
        header:{
            "Content-Type":"application/x-www-form-urlencoded"
        }
    }
    const axiosBody = {
        username : email,
        password : password
    }
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

    const onClickLogin = (e) => {
        e.preventDefault();
        const formdata = new FormData();
        formdata.append("username", email);
        formdata.append("password", password);

        fetch("http://localhost:8080/login", {
            method:"POST", headers:{
                "Content-Type": "multipart/form-data",},
            body: formdata,
        }).then( (res) => {
                alert("로그인 완료")
                navigate("/articles")
            })
            .catch();



        // axios.post("http://localhost:8080/login", qs.stringify(axiosBody), axiosConfig)
        //     .then((res) => {
        //     alert(res.data)
        //     // if(res.data.email === undefined){
        //     //     alert("입력하신 id가 존재하지 않습니다")
        //     // } else if (res.data.email === null){
        //     //     alert("입력하신 비밀번호가 일치하지 않습니다")
        //     // } else if (res.data.email === email){
        //     //     sessionStorage.setItem("email", email);
        //     //     sessionStorage.setItem("password", password);
        //     // }
        //     //document.location.href = "/";
        // })
        //     .catch((err) => {
        //         alert('에러 발생')
        //     });
    };

    const backToArticleList = () => {
        navigate('/articles')
    }

    return (
        <div style={{
            display: 'flex', justifyContent: 'center', alignItems: 'center',
            width: '100%', height: '100vh'
        }}>
            <h2>로그인</h2>
            <form id="formdata" style={{ display: 'flex', flexDirection: 'column'}}>
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