import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import AddArticle from './AddArticle';
import EditArticle from "./EditArticle";
import BlogList from "./BlogList";

const AppRouter = () => {
    return(
        <div>
            <BrowserRouter>
                <div style={style}>
                    <Routes>
                        <Route exact path="/" element={<BlogList/>}/>
                        <Route path="/Articles" element={<BlogList/>}/>
                        <Route path="/addArticle" element={<AddArticle/>}/>
                        <Route path="/editArticle" element={<EditArticle/>}/>
                    </Routes>

                </div>
            </BrowserRouter>
        </div>
    );
}
const style ={
    marginTop: '20px'
}

export default AppRouter;