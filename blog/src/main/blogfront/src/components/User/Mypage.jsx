import React, {useEffect, useState} from "react";
import ApiService from "../../api/ApiService";
import {useNavigate} from "react-router-dom";

function Mypage(){
    const navigate = new useNavigate();
    const [isShow, setIsshow] = useState(false);
    let [userinfo, setUserinfo] = useState({
        email:'',
        nickname : '',
        provider : ''
    });
    let {email, nickname, provider} = userinfo

    useEffect(() => {
        if(window.localStorage.getItem("id"))
        {   ApiService.userinfo()
            .then((res) => {
                //console.log(res)
                setUserinfo(res.data);
            })
            .catch()
        }

        else {
            ApiService.oauthuserinfo()
                .then((res) => {
                    //console.log(res)
                    setUserinfo(res.data);
                })
                .catch()
        }
    }, []);





    const goToLogout = () => {
        window.localStorage.clear()
        ApiService.logout()
            .then( (res) => {
                    //console.log(res)
                }
            )
        alert("로그아웃 완료")
        navigate('/')
    }

    const setuserinfo = () => {
        ApiService.userinfo()
            .then((res) => {

            })
    }


    const notShow = () => {
        setIsshow(false);
    };
    const onShow = () => {
        setIsshow(true);
    };

    return (
        <div className="px-4 py-5 my-5 text-center" >
                <h1 className="display-5 fw-bold text-body-emphasis">My page</h1>
                <div className="col-lg-6 mx-auto">
                    <br/>
                    <br/>
                    <p className="lead mb-4">안녕하세요</p>
                    <p className="lead mb-4">{userinfo.email} 님 ! </p>
                    <p className="lead mb-4"> 닉네임 : {userinfo.nickname} </p>

                    <div className="d-grid gap-2 d-sm-flex justify-content-sm-center">
                        <button type="button" className="btn btn-outline-primary btn-sm px-4 gap-3" onClick={onShow}>닉네임변경</button>
                    </div>
                    <br/>
                    {isShow === true ? <div>
                        <form>
                            <input type='nickname' value={nickname}/>
                            <button type="submit" className="btn btn-outline-primary btn-sm px-4 gap-3" onClick={notShow}>변경</button>
                        </form> </div> : null}
                    <br/>
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