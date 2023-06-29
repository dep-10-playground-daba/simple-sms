import express from "express";
import {datasource} from "../db/dbcp";

export const router = express.Router();


type Course = {
    "id":string,
    "description": string,
    "duration": number
}

router.get('/',async (req, res)=>{
    const result=await datasource.query('SELECT * FROM course')
    res.status(200).json(result)
})
router.post('/', async (req, res) => {
    // const customer = <Customer> req.body;
    const course = req.body as Course;
    if (!course.id||!course.description || !course.duration) {
        res.status(400).send("Invalid data");
    } else {
        try {
            const result = await datasource
                .query('INSERT INTO course (id,description,duration) VALUES (?, ?,?)',
                    [course.id,course.description, course.duration]);
            res.status(201).json(course);
        }catch (err: any){
                throw err;
            }
    }
});
router.delete('/:id', async (req, res) => {
    const result = await datasource.query('DELETE FROM course WHERE id = ?', [req.params.id])
    res.sendStatus(result.affectedRows ? 204 : 404);
});

router.patch('/:id', async (req, res) => {
    const course = req.body as Course;
    if (!course.id?.trim()){
        res.sendStatus(400);
        return;
    }
    if(!course.description?.trim()){
        res.sendStatus(400);
        return;
    }
    if(!course.duration){
        res.sendStatus(400);
        return;
    }
    const result = await datasource.query("UPDATE course SET description = ?,duration=? WHERE id = ?",
        [course.description, course.duration,req.params.id]);
    res.sendStatus(result.affectedRows ? 204 : 404);
});