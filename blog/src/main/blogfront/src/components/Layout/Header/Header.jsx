import styles from "./Header.module.css";
import {useNavigate} from "react-router-dom";
const Header = () => {
    const navigate = new useNavigate();

    const goToLoginForm = () => {
        navigate('/login')
    }

    const goToSignupForm = () => {
        navigate('/signup')
    }
    const backToArticleList = () => {
        navigate('/articles')
    }

    return (
        <div className={styles.header}>
            <h2 onClick={backToArticleList}>Header</h2>
            <nav>
                <ul>
                    <li>메뉴 1</li>
                    <li>메뉴 2</li>
                    <li>메뉴 3</li>
                    <button className={styles.button} onClick={goToLoginForm}>로그인</button>
                    <button className={styles.button} onClick={goToSignupForm}>회원가입</button>
                </ul>
            </nav>
        </div>

    )
}

export default Header