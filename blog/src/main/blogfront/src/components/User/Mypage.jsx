import React from "react";
import ApiService from "../../api/ApiService";
import {useNavigate} from "react-router-dom";

function Mypage(){
    const navigate = new useNavigate();
    const correntuser = window.localStorage.getItem("id")
    const goToLogout = () => {
        window.localStorage.clear()
        ApiService.logout()
            .then( (res) => {
                    console.log(res)
                }
            )
        alert("로그아웃 완료")
        navigate('/')
    }
    return (
        <div className="px-4 py-5 my-5 text-center">
                <h1 className="display-5 fw-bold text-body-emphasis">My page</h1>
                <div className="col-lg-6 mx-auto">
                    <br/>
                    <br/>
                    <p className="lead mb-4">안녕하세요</p>
                    <p className="lead mb-4">{correntuser} 님, </p>
                    <br/>
                    <div className="d-grid gap-2 d-sm-flex justify-content-sm-center">
                        <button type="button" className="btn btn-primary btn-lg px-4 gap-3" onClick={goToLogout}>로그아웃</button>
                        <button type="button" className="btn btn-outline-secondary btn-lg px-4">회원탈퇴</button>
                    </div>
                </div>
        </div>
    )
}

export default Mypage;