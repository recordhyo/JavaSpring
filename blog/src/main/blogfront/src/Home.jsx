import React from "react";
import homeimg from "./IMG_0074.JPG"
const Home = () => {
    return (
        <div style={style}>
            <div>
                <img alt="homeimage" src={homeimg} width="500px"/>
                <br/>
                <br/>
                <br/>
                <h1 align="center">안녕하세요...</h1>
            </div>
        </div>

    )
}
const style = {
    display : 'flex',
    justifyContent: 'center'
}
export default Home