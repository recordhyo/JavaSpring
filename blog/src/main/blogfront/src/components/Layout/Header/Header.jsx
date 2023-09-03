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

                    <a href="/" className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
                        <svg className="bi me-2" width="40" height="32"><use xlinkhref="#bootstrap"/></svg>
                        <span className="fs-4">recordhyo</span>
                    </a>
                    <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="/" className="nav-link px-2 link-secondary" aria-current="page">HOME</a></li>
                        <li><a href="/articles" className="nav-link px-2 link-body-emphasis" >BLOG</a></li>
                        <li><a href="/" className="nav-link px-2 link-body-emphasis">CONTACT</a></li>
                    </ul>
                    <div className="text-end">
                    <form className="mb-lg-0 me-lg-3 d-inline-block w-25" role="search">
                        <input type="search" className="form-control" placeholder="Search..." aria-label="Search" />
                    </form>

                        <button className="btn btn-outline-dark me-1" onClick={goToLoginForm}>로그인</button>
                        <button className="btn btn-secondary" onClick={goToSignupForm}>회원가입</button>
                    </div>

                </header>
            </div>
        )
    }
    else {
        return (
            <div className="container">
                <header className="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">

                    <a href="/" className="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
                        <svg className="bi me-2" width="40" height="32"><use xlinkhref="#bootstrap"/></svg>
                        <span className="fs-4">recordhyo</span>
                    </a>
                    <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="/" className="nav-link px-2 link-secondary" aria-current="page">HOME</a></li>
                        <li><a href="/articles" className="nav-link px-2 link-body-emphasis" >BLOG</a></li>
                        <li><a href="/" className="nav-link px-2 link-body-emphasis">CONTACT</a></li>
                    </ul>
                    <div className="text-end">
                    <form className="mb-lg-0 me-lg-3 d-inline-block w-25" role="search">
                        <input type="search" className="form-control" placeholder="Search..." aria-label="Search" />
                    </form>

                        <button className="btn btn-outline-dark me-1" onClick={goToLogout}>로그아웃</button>
                        <button className="btn btn-secondary" onClick={goToMypage}>마이페이지</button>
                    </div>

                </header>
            </div>
        )
    }
}

export default Header