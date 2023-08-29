import React, {useEffect, useState} from "react";
import ApiService from "./ApiService";

function Login() {
    const [id, setId] = useState('')
    const [pw, setPw] = useState('')

    useEffect(() => {
        ApiService.loginpage()
            .then(res => console.log(res)).catch()
    }, []);

    return (
        <div>
            <form>
                <label>Email</label>
                <input type='email' value={id} />
                <br/>
                <label>Password</label>
                <input type='password' value={pw}/>
                <br/>
                <button formAction=''>
                    Login
                </button>
            </form>

        </div>

    )
}

export default Login;