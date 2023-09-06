import React, {useEffect, useState} from "react";
import ApiService from "../../api/ApiService";
import axios from "axios";
import {useNavigate} from "react-router-dom";

function Signup() {
    const navigate = new useNavigate();
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [nickname, setNickname] = useState('')

    useEffect(() => {
        ApiService.signuppage()
            .then().catch()
    }, []);

    const onEmailHandler = (e) => {
        setEmail(e.currentTarget.value);
    }

    const onPasswordHandler = (e) => {
        setPassword(e.currentTarget.value);
    }

    const onNicknameHandler = (e) => {
        setNickname(e.currentTarget.value);
    }

    const onClickSignup = () => {
        axios.post("/signup", {
            email: email,
            password: password,
            nickname: nickname
        }).then( (res) => {
            console.log(res.data)
            alert("회원가입 완료")
        })
            .catch();
    }

    const backTohome = () => {
        navigate('/')
    }

    return (
        <div style={{
            display: 'flex', justifyContent: 'center', alignItems: 'center',
        }}>

            <form style={{ display: 'flex', flexDirection: 'column'}}>
                <h2>회원가입</h2>
                <label>Email</label>
                <input type='email' value={email} onChange={onEmailHandler}/>
                <label>Password</label>
                <input type='password' value={password} onChange={onPasswordHandler}/>
                <label>닉네임</label>
                <input type='nickname' value={nickname} onChange={onNicknameHandler}/>
                <br />
                <button className="btn btn-secondary" onClick={ () => {onClickSignup(); backTohome()}}>
                    회원가입
                </button>
            </form>
        </div>

    )
}

export default Signup;