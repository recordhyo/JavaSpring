import styles from "./Header.module.css";
import {useNavigate} from "react-router-dom";
import ApiService from "../../../api/ApiService";
import 'bootstrap/dist/css/bootstrap.css';
import {Person} from 'react-bootstrap-icons'

const Header = () => {
    const navigate = new useNavigate();
    const correntuser = sessionStorage.getItem("id")

    const goToLoginForm = () => {
        navigate('/userlogin')
    }

    const goToSignupForm = () => {
        navigate('/usersignup')
    }

    const goToMypage = () => {
        navigate('/')
    }

    const goToLogout = () => {
        window.sessionStorage.clear()
        ApiService.logout()
            .then( (res) => {
                console.log(res)
            }
        )
        alert("로그아웃 완료")
        navigate('/')
    }
    const backToHome = () => {
        navigate('/')
    }

    const goToArticle = () => {
        navigate('articles')
    }

    if (!correntuser) {
        return (
            <div className="container">
                <header className="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">

                    <div className="col-md-3 mb-2 mb-md-0">
                        <a href="/" className="d-inline-flex link-body-emphasis text-decoration-none">
                            <svg className="bi" width="40" height="32" role="img" aria-label="Bootstrap"><use xlinkHref="/"/></svg>
                        </a>
                    </div>
                    <ul className="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                        <li onClick={backToHome} className="nav-link px-2 link-secondary" >HOME</li>
                        <li onClick={goToArticle} className="nav-link px-2" >BLOG</li>
                        <li className="nav-link px-2" >메뉴 3</li>
                    </ul>
                    <div className="col-md-3 text-end">
                        <button className="btn btn-outline-primary me-2" onClick={goToLoginForm}><Person />로그인</button>
                        <button className="btn btn-primary" onClick={goToSignupForm}>회원가입</button>
                    </div>

                </header>
            </div>
        )
    }
    else {
        return (
            <div className="container">
            <header className="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">

                <div className="col-md-3 mb-2 mb-md-0">
                    <a href="/" className="d-inline-flex link-body-emphasis text-decoration-none">
                        <svg className="bi" width="40" height="32" role="img" aria-label="Bootstrap"><use xlinkHref="/" /></svg>
                    </a>
                </div>
                <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                    <li onClick={backToHome} className="nav-link px-2 link-secondary" >HOME</li>
                    <li onClick={goToArticle} className="nav-link px-2" >BLOG</li>
                    <li className="nav-link px-2" >메뉴 3</li>
                </ul>
                <div className="col-md-3 text-end">
                    <button className="btn btn-outline-primary me-2" onClick={goToLogout}><Person />로그아웃</button>
                    <button className="btn btn-primary" onClick={goToMypage}>마이페이지</button>
                </div>

            </header>
            </div>
        )
    }
}

export default Header