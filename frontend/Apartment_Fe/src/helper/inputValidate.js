import Joi from 'joi';

const inputValidate = (data) => {
    const schema = Joi.string().min(2).required();
    //validate
    return schema.validate(data);
};

export { inputValidate };
