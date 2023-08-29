import styles from "./Header.module.css";
const Header = () => {
    return (
        <div className={styles.header}>
            <h2>Header</h2>
            <nav>
                <ul>
                    <li>메뉴 1</li>
                    <li>메뉴 2</li>
                    <li>메뉴 3</li>
                    <button className={styles.button}>로그인</button>
                    <button className={styles.button}>회원가입</button>
                </ul>
            </nav>
        </div>

    )
}

export default Header