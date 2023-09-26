import React, {useEffect, useState} from "react";
import ApiService from "../../api/ApiService";
import {useNavigate} from "react-router-dom";
import axios from "axios";

function Mypage(){
    const navigate = new useNavigate();
    const [isShow, setIsshow] = useState(false);
    const [newnickname, setNewNickname] = useState('')
    let [userinfo, setUserinfo] = useState({
        email:'',
        nickname : '',
        provider : ''
    });
    let {email, nickname, provider} = userinfo
    let email_p = email+provider;
    useEffect(() => {
        if(window.localStorage.getItem("id"))
        {   ApiService.userinfo()
            .then((res) => {
                //console.log(res.data.email)
                setUserinfo(res.data);
            })
            .catch()
        }

        else {
            ApiService.oauthuserinfo()
                .then((res) => {
                    console.log(res)
                    setUserinfo(res.data);
                })
                .catch()
        }
    }, []);

    const onNewNicknameHandler = (e) => {
        setNewNickname(e.currentTarget.value);
    }
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


    const deleteUser = () => {
        if (window.confirm("정말 탈퇴하시겠습니까?")) {
            ApiService.deleteuser()
                .then(function (response) {
                    alert("탈퇴가 완료되었습니다")
                    console.log(response)
                    window.localStorage.clear()
                    navigate('/')
                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
                })
        } else {
            alert("계속 이용해주세요~");
        }
    }


    const notShow = () => {
        ApiService.changeNickname(newnickname)
            .then((res) =>
                console.log(res))
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
                            <input type='nickname' defaultValue={nickname} name="newnickname" onChange={onNewNicknameHandler}/>
                            <button type="submit" className="btn btn-outline-primary btn-sm px-4 gap-3" onClick={notShow}>변경</button>
                        </form> </div> : null}
                    <br/>
                    <br/>
                    <div className="d-grid gap-2 d-sm-flex justify-content-sm-center">
                        <button type="button" className="btn btn-primary btn-lg px-4 gap-3" onClick={goToLogout}>로그아웃</button>
                        <button type="button" className="btn btn-outline-secondary btn-lg px-4" onClick={deleteUser}>회원탈퇴</button>
                    </div>
                </div>
        </div>
    )
}

export default Mypage;