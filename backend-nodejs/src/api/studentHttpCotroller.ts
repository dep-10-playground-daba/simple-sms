import express from "express";
import {datasource} from "../db/dbcp";

export const router = express.Router();


type Student = {
    "id":string,
    "name": string,
    "address": string
}

router.get('/',(req, res)=>{
    datasource.query
})

router.delete('/:isbn', async (req, res) => {
    const result = await datasource.query('DELETE FROM book WHERE isbn = ?', [req.params.isbn])
    res.sendStatus(result.affectedRows ? 204 : 404);
});

router.patch('/:isbn', async (req, res) => {
    const book = req.body as Book;
    if (!book.title?.trim()){
        res.sendStatus(400);
        return;
    }
    const result = await datasource.query("UPDATE book SET title = ? WHERE isbn = ?",
        [book.title, req.params.isbn]);
    res.sendStatus(result.affectedRows ? 204 : 404);
});