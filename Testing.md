# Testing


After build
* send complaint using 
```bash
curl -H "Content-type: application/json" -d '{"company": "apple", "description": "my escape key is missing. Please send me one"}' localhost:8080
```
response
```bash
283aa9b2-5d7c-439f-8bac-77e021c7db13
```
To check the result open a browser at [http://localhost:8080](http://localhost:8080)
this shows:
```json
[
    {
        "complaintId": "283aa9b2-5d7c-439f-8bac-77e021c7db13",
        "company": "apple",
        "description": "my escape key is missing. Please send me one"
    }
]
```
Or just find one by going to [http://localhost:8080/283aa9b2-5d7c-439f-8bac-77e021c7db13](http://localhost:8080/283aa9b2-5d7c-439f-8bac-77e021c7db13)
this shows only one entry:
```json
{
    "complaintId": "283aa9b2-5d7c-439f-8bac-77e021c7db13",
    "company": "apple",
    "description": "my escape key is missing. Please send me one"
}
```

Add another complaint:
```bash
curl -H "Content-type: application/json" -d '{"company": "microsoft", "description": "Bash does not always work nicely"}' localhost:8080
ff92a564-1ad1-4afc-8fa3-0319ad52e7e5
```
response
```
ff92a564-1ad1-4afc-8fa3-0319ad52e7e5
```
The browser at [http://localhost:8080](http://localhost:8080) shows:
```json
[

    {
        "complaintId": "283aa9b2-5d7c-439f-8bac-77e021c7db13",
        "company": "apple",
        "description": "my escape key is missing. Please send me one"
    },
    {
        "complaintId": "ff92a564-1ad1-4afc-8fa3-0319ad52e7e5",
        "company": "microsoft",
        "description": "Bash does not always work nicely"
    }

]
```